"""
경기도 공공시설(공원·도서관·체육시설) → Firestore places 컬렉션 추가
+ 기존 서울 문서에 region="서울" 백필
+ places_meta 버전 +1

필터 기준:
- 공원: 면적 3,000㎡ 이상 + 좌표·이름·주소 유효
- 도서관: 공공도서관만
- 체육시설: 공공 + 정상운영 + 게이트볼장 제외
"""
import csv
import firebase_admin
from firebase_admin import credentials, firestore

KEY = r"C:\Users\jeehh\Desktop\대외활동\2026 서울시 빅데이터 활용 창업 대회\퀘스트, ai모델\onestep_firebase_key.json"
LIB_CSV  = r"C:\Users\jeehh\Desktop\도서관현황(제공표준).csv"
PARK_CSV = r"C:\Users\jeehh\Desktop\도시공원정보현황(제공표준).csv"
GYM_CSV  = r"C:\Users\jeehh\Desktop\경기도체육시설현황.csv"

PARK_MIN_AREA = 3000.0

def fnum(s):
    try: return float(str(s).strip())
    except: return None

def load_libraries():
    out = []
    with open(LIB_CSV, encoding="cp949") as f:
        for r in csv.DictReader(f):
            if r["도서관유형"].strip() != "공공도서관":
                continue
            lat, lng = fnum(r["위도"]), fnum(r["경도"])
            name = r["도서관명"].strip()
            addr = r["소재지도로명주소"].strip()
            if lat is None or lng is None or not name or not addr:
                continue
            district = (r["시군구명"].strip() or r["시군명"].strip())
            out.append({"type": "library", "name": name, "lat": lat, "lng": lng,
                        "district": district, "region": "경기"})
    return out

def load_parks():
    out = []
    with open(PARK_CSV, encoding="cp949") as f:
        for r in csv.DictReader(f):
            lat, lng = fnum(r["위도"]), fnum(r["경도"])
            area = fnum(r["공원면적(㎡)"])
            name = r["공원명"].strip()
            addr = r["소재지도로명주소"].strip() or r["소재지지번주소"].strip()
            if lat is None or lng is None or area is None or not name or not addr:
                continue
            if area < PARK_MIN_AREA:
                continue
            parts = addr.split()
            district = parts[1] if len(parts) >= 2 else ""
            out.append({"type": "park", "name": name, "lat": lat, "lng": lng,
                        "district": district, "region": "경기"})
    return out

def load_gyms():
    out = []
    with open(GYM_CSV, encoding="cp949") as f:
        for r in csv.DictReader(f):
            if r["시설구분명"].strip() != "공공":
                continue
            if r["시설상태명"].strip() != "정상운영":
                continue
            if "게이트볼" in r["업종명"]:
                continue
            lat, lng = fnum(r["정제WGS84위도"]), fnum(r["정제WGS84경도"])
            name = r["시설명"].strip()
            if lat is None or lng is None or not name:
                continue
            out.append({"type": "gym", "name": name, "lat": lat, "lng": lng,
                        "district": r["시군구명"].strip(), "region": "경기"})
    return out

def main():
    cred = credentials.Certificate(KEY)
    firebase_admin.initialize_app(cred)
    db = firestore.client()

    libs, parks, gyms = load_libraries(), load_parks(), load_gyms()
    gyeonggi = libs + parks + gyms
    print(f"[경기] 도서관 {len(libs)}, 공원 {len(parks)}, 체육시설 {len(gyms)} → 총 {len(gyeonggi)}")

    # 1) 기존 서울 문서에 region 백필
    existing = db.collection("places").stream()
    backfill = 0
    batch = db.batch()
    cnt = 0
    for doc in existing:
        data = doc.to_dict()
        if not data.get("region"):
            batch.update(doc.reference, {"region": "서울"})
            backfill += 1
            cnt += 1
            if cnt >= 400:
                batch.commit(); batch = db.batch(); cnt = 0
    if cnt > 0:
        batch.commit()
    print(f"[서울] region 백필: {backfill}건")

    # 2) 경기 데이터 신규 업로드
    batch = db.batch(); cnt = 0; uploaded = 0
    for p in gyeonggi:
        ref = db.collection("places").document()
        batch.set(ref, p)
        cnt += 1; uploaded += 1
        if cnt >= 400:
            batch.commit(); batch = db.batch(); cnt = 0
    if cnt > 0:
        batch.commit()
    print(f"[경기] 업로드: {uploaded}건")

    # 3) 버전 +1
    meta_ref = db.collection("meta").document("places_meta")
    meta_doc = meta_ref.get()
    cur = meta_doc.to_dict().get("version", 0) if meta_doc.exists else 0
    meta_ref.set({"version": cur + 1})
    print(f"[meta] places 버전: {cur} → {cur + 1}")

if __name__ == "__main__":
    main()
