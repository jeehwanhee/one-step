"""
체육시설 {체육시설} 템플릿 퀘스트를 quests 컬렉션에 추가
+ quests_meta 버전 +1

- 기존 quests에서 max index 조회 후 그 다음 번호부터 부여
- 난이도별 EXP는 기존 퀘스트 평균값에 맞춤
"""
import firebase_admin
from firebase_admin import credentials, firestore

KEY = r"C:\Users\jeehh\Desktop\대외활동\2026 서울시 빅데이터 활용 창업 대회\퀘스트, ai모델\onestep_firebase_key.json"

# 난이도별 템플릿 (EXP는 기존 평균으로 자동 대체)
TEMPLATES = [
    (1, "{체육시설} 위치와 운영시간 검색해보기", "어떤 시설인지 알게 된 점을 적어주세요"),
    (2, "{체육시설}에서 어떤 운동을 할 수 있는지 알아보기", "해보고 싶은 운동이 있었나요?"),
    (4, "{체육시설}까지 걸어가 보기", "가는 길에 어떤 기분이 들었나요?"),
    (5, "{체육시설}에서 10분간 가벼운 몸 풀기", "운동 후 몸과 마음이 어땠나요?"),
]

def main():
    cred = credentials.Certificate(KEY)
    firebase_admin.initialize_app(cred)
    db = firestore.client()

    # 기존 퀘스트 로드
    docs = list(db.collection("quests").stream())
    quests = [d.to_dict() for d in docs]
    print(f"기존 퀘스트 {len(quests)}개")

    # max index
    max_index = max((q.get("index", 0) for q in quests), default=0)

    # 난이도별 평균 EXP 계산
    from collections import defaultdict
    exp_by_diff = defaultdict(list)
    for q in quests:
        d = q.get("difficulty")
        e = q.get("questEXP")
        if isinstance(d, int) and isinstance(e, int):
            exp_by_diff[d].append(e)
    avg_exp = {d: round(sum(v) / len(v)) for d, v in exp_by_diff.items()}
    print("난이도별 평균 EXP:", dict(sorted(avg_exp.items())))

    # 기본 EXP (해당 난이도 데이터 없을 때)
    fallback_exp = {1: 5, 2: 8, 3: 12, 4: 18, 5: 25}

    # 업로드
    idx = max_index + 1
    added = 0
    for diff, name, question in TEMPLATES:
        exp = avg_exp.get(diff, fallback_exp[diff])
        doc = {
            "index": idx,
            "questName": name,
            "difficulty": diff,
            "confirmQuestion": question,
            "questEXP": exp,
        }
        db.collection("quests").document(str(idx)).set(doc)
        print(f"  + index {idx}: [{diff}성/{exp}XP] {name}")
        idx += 1
        added += 1

    # 버전 +1
    meta_ref = db.collection("meta").document("quests_meta")
    meta_doc = meta_ref.get()
    cur = meta_doc.to_dict().get("version", 0) if meta_doc.exists else 0
    meta_ref.set({"version": cur + 1})
    print(f"\n[meta] quests 버전: {cur} → {cur + 1}")
    print(f"[완료] 체육시설 퀘스트 {added}개 추가")

if __name__ == "__main__":
    main()
