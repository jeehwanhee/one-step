"""
places.csv → Firestore 'places' 컬렉션 업로드
- 사전 준비: pip install firebase-admin
- 서비스 계정 키 필요: scripts/serviceAccountKey.json
"""
import csv
import os
import sys

import firebase_admin
from firebase_admin import credentials, firestore

SERVICE_KEY = r"C:\Users\jeehh\Desktop\대외활동\2026 서울시 빅데이터 활용 창업 대회\퀘스트, ai모델\onestep_firebase_key.json"
CSV_PATH    = r"C:\Users\jeehh\Desktop\Android\oneStep_ai\app\src\main\assets\places.csv"
COLLECTION  = "places"

def main():
    if not os.path.exists(SERVICE_KEY):
        print(f"[ERROR] 서비스 키 파일 없음: {SERVICE_KEY}")
        print("Firebase Console → 설정 → 서비스 계정에서 키 다운로드 후 위 경로에 저장하세요.")
        sys.exit(1)

    cred = credentials.Certificate(SERVICE_KEY)
    firebase_admin.initialize_app(cred)
    db = firestore.client()

    # CSV 읽기
    with open(CSV_PATH, encoding="utf-8") as f:
        rows = list(csv.DictReader(f))
    print(f"[INFO] CSV 로드: {len(rows)}개")

    # 기존 데이터 확인
    existing = list(db.collection(COLLECTION).limit(1).stream())
    if existing:
        ans = input(f"⚠️ '{COLLECTION}' 컬렉션에 이미 데이터가 있습니다. 모두 삭제하고 재업로드할까요? (y/N): ")
        if ans.lower() == "y":
            print("[INFO] 기존 데이터 삭제 중...")
            for doc in db.collection(COLLECTION).stream():
                doc.reference.delete()
            print("[INFO] 삭제 완료")
        else:
            print("[INFO] 추가 모드로 진행")

    # 배치 업로드 (Firestore 배치 한도 500)
    BATCH_SIZE = 400
    total = 0
    for i in range(0, len(rows), BATCH_SIZE):
        batch = db.batch()
        chunk = rows[i:i + BATCH_SIZE]
        for r in chunk:
            doc_ref = db.collection(COLLECTION).document()
            batch.set(doc_ref, {
                "type": r["type"],
                "name": r["name"],
                "lat": float(r["lat"]),
                "lng": float(r["lng"]),
                "district": r["district"],
            })
        batch.commit()
        total += len(chunk)
        print(f"[INFO] {total}/{len(rows)} 업로드됨")

    print(f"[OK] 총 {total}개 업로드 완료")

    # 버전 키 +1 (앱이 캐시 무효화하도록 신호)
    meta_ref = db.collection('meta').document('places_meta')
    meta_doc = meta_ref.get()
    current_version = meta_doc.to_dict().get('version', 0) if meta_doc.exists else 0
    new_version = current_version + 1
    meta_ref.set({'version': new_version})
    print(f"[INFO] places 버전 업데이트: {current_version} → {new_version}")

if __name__ == "__main__":
    main()
