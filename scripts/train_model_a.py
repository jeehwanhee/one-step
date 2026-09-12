"""
Model_A (고립도) 재학습 — 공식 통계 기반 베이지안 우도비(LLR) 라벨 + XGBoost
- 라벨: 서울시 고립은둔 실태조사(고립은둔 486 vs 미해당 5027)에서 직접 계산한
        log-likelihood ratio 합 → sigmoid (유의성 게이팅 p<0.05)
- 입력(앱 전송 형식, 8개):
   [0]age [1]gender [2]shower(주간0-7) [3]out_freq(0-4)
   [4]active_time(0-3) [5]hiki(개월) [6]sleep_hours [7]meal(일일횟수)
- 산출: model_a_train.csv, Model_A.java
"""
import glob, math
import numpy as np
import pandas as pd
from openpyxl import load_workbook
import xgboost as xgb
import m2cgen as m2c

SURVEY = glob.glob(r'C:\Users\jeehh\Downloads\*TABLE*전체*230127.xlsx')[0]
N_ISO, N_NORM = 486, 5027
ISO_COL, NORM_COL = 14, 15

ws = load_workbook(SURVEY, data_only=True)['테이블']
rows = list(ws.iter_rows(values_only=True))

def find(kw):
    for i in range(len(rows)):
        if rows[i][0] and str(rows[i][0]).strip().startswith(kw):
            return i
    return -1

def zt(pi, pn):
    p1, p2 = pi/100, pn/100
    pool = (p1*N_ISO + p2*N_NORM)/(N_ISO+N_NORM)
    se = math.sqrt(pool*(1-pool)*(1/N_ISO+1/N_NORM)) if pool > 0 else 1
    z = (p1-p2)/se if se > 0 else 0
    from math import erf, sqrt
    return 2*(1-0.5*(1+erf(abs(z)/sqrt(2))))

def extract(kw):
    """(iso분포, norm분포, gated_logLR) 반환"""
    idx = find(kw)
    base = next(j for j in range(idx, idx+8) if rows[j][0] and '사례수' in str(rows[j][0]))
    iso_d, norm_d = [], []
    for j in range(base+1, base+25):
        label = str(rows[j][0]).strip() if rows[j][0] else ''
        if '▣' in label: break
        if not label: continue
        v14, v15 = rows[j][ISO_COL], rows[j][NORM_COL]
        if v14 is None or v15 is None: continue
        try: iso, norm = float(v14), float(v15)
        except: continue
        iso_d.append(iso); norm_d.append(norm)
    iso_a = np.array(iso_d); norm_a = np.array(norm_d)
    iso_a /= iso_a.sum(); norm_a /= norm_a.sum()
    loglr = []
    for i in range(len(iso_d)):
        p = zt(iso_d[i], norm_d[i])
        lr = math.log(max(iso_a[i], 1e-4)/max(norm_a[i], 1e-4))
        loglr.append(0.0 if p >= 0.05 else lr)   # 유의성 게이팅
    return iso_a, norm_a, np.array(loglr)

# 6개 행동 변수 (동거인수·age·gender는 라벨에 미반영 = 노이즈 입력)
VARS = {
    "out":    "【A7】",   # 외출
    "hiki":   "【A3】",   # 은둔기간
    "meal":   "【B7】",   # 식사
    "active": "【B3】",   # 활동시간대
    "sleep":  "【B2_R】", # 수면
    "shower": "【B9_2】", # 샤워
}
model = {k: extract(v) for k, v in VARS.items()}

# ── 설문 카테고리 → 앱 입력값 변환 ──────────────────────
# 외출 A7[0..7] → out_freq(0-4)  (firebaseManager 매핑 척도)
OUT_FREQ = [4, 3, 3, 2, 1, 1, 0, 0]
# 활동 B3[0..4] → activeTime(0-3); '없음'(4)은 앱에 없어 새벽(0)으로
ACTIVE   = [0, 1, 2, 3, 0]
# 식사 B7[0..6] → 일일 횟수
MEAL     = [4, 3, 2, 1, 0, 0, 2]
# 샤워 B9_2[0..4] → 주간 횟수(0-7)
SHOWER   = [0, 0, 1, 3, 7]
# 은둔 A3[0..7] → 개월 (버킷 범위 → 균등 샘플)
HIKI_RANGE = [(0,3),(3,6),(6,12),(12,36),(36,60),(60,84),(84,120),(120,240)]
# 수면 B2_R[0..4] → 시간 (버킷 범위 → 균등 샘플)
SLEEP_RANGE = [(2,4),(4,8),(8,12),(12,16),(16,20)]

def gen_dataset(n=4000, pi=0.5, seed=42):
    rng = np.random.default_rng(seed)
    z = (rng.random(n) < pi).astype(int)  # 1=iso, 0=norm

    def sample_cat(key, g):
        iso, norm, _ = model[key]
        p = iso if g == 1 else norm
        return rng.choice(len(p), p=p)

    recs = []
    for i in range(n):
        g = z[i]
        c = {k: sample_cat(k, g) for k in VARS}

        # 라벨 = 게이팅 logLR 합 → sigmoid (통계 기반)
        logit = sum(model[k][2][c[k]] for k in VARS)
        label = 1/(1+math.exp(-logit))

        # 앱 입력값 변환
        age    = rng.integers(19, 39)
        gender = rng.integers(0, 2)
        shower = SHOWER[c["shower"]]
        outf   = OUT_FREQ[c["out"]]
        active = ACTIVE[c["active"]]
        hlo, hi = HIKI_RANGE[c["hiki"]];  hiki = round(rng.uniform(hlo, hi))
        slo, shi = SLEEP_RANGE[c["sleep"]]; sleep = round(rng.uniform(slo, shi))
        meal   = MEAL[c["meal"]]

        recs.append([age, gender, shower, outf, active, hiki, sleep, meal, label])

    cols = ["age","gender","shower","out_freq","active_time","hiki_period","sleep_hours","meal_count","isolation"]
    return pd.DataFrame(recs, columns=cols)

df = gen_dataset(4000)
df.to_csv("model_a_train.csv", index=False, encoding="utf-8-sig")
print(f"학습 데이터 {len(df)}건 생성, isolation mean={df['isolation'].mean():.3f} std={df['isolation'].std():.3f}")

# ── XGBoost 학습 ────────────────────────────────────
feat = ["age","gender","shower","out_freq","active_time","hiki_period","sleep_hours","meal_count"]
X, y = df[feat], df["isolation"]
mdl = xgb.XGBRegressor(
    n_estimators=120, max_depth=4, learning_rate=0.1,
    objective="reg:logistic", random_state=42,
)
mdl.fit(X, y)
print("학습 완료")

# ── Java 변환 ───────────────────────────────────────
java = m2c.export_to_java(mdl, class_name="Model_A")
with open("Model_A.java", "w", encoding="utf-8") as f:
    f.write(java)
print("Model_A.java 생성 완료")

# 검증: 변수 중요도
imp = sorted(zip(feat, mdl.feature_importances_), key=lambda x: -x[1])
print("\n변수 중요도:")
for f_, v in imp:
    print(f"  {f_:<14} {v:.3f}")
