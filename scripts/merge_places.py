"""
서울시 공원·도서관 CSV → 통합 places.csv 변환
- 서울 25개 자치구만 포함
- 도서관은 공공도서관만 필터
- 출력: app/src/main/assets/places.csv
"""
import csv
import os

PARK_CSV    = r"C:\Users\jeehh\Desktop\서울시 주요 공원현황.csv"
LIBRARY_CSV = r"C:\Users\jeehh\Desktop\서울시 공공도서관 현황정보.csv"
OUTPUT_CSV  = r"C:\Users\jeehh\Desktop\Android\oneStep_ai\app\src\main\assets\places.csv"

SEOUL_DISTRICTS = {
    "종로구", "중구", "용산구", "성동구", "광진구", "동대문구", "중랑구",
    "성북구", "강북구", "도봉구", "노원구", "은평구", "서대문구", "마포구",
    "양천구", "강서구", "구로구", "금천구", "영등포구", "동작구", "관악구",
    "서초구", "강남구", "송파구", "강동구"
}

def clean_name(name: str) -> str:
    """이름 정제: 공백 정리 + 너무 긴 이름 단축"""
    name = name.strip().replace("  ", " ")
    return name

def read_parks():
    """공원 CSV → list of dict"""
    rows = []
    with open(PARK_CSV, encoding="cp949") as f:
        reader = csv.DictReader(f)
        for r in reader:
            district = r.get("지역", "").strip()
            if district not in SEOUL_DISTRICTS:
                continue  # 서울 외 제외 (과천, 고양 등)

            name = clean_name(r.get("공원명", ""))
            lat_raw = r.get("Y좌표(WGS84)", "").strip()
            lng_raw = r.get("X좌표(WGS84)", "").strip()
            if not name or not lat_raw or not lng_raw:
                continue
            try:
                lat = float(lat_raw)
                lng = float(lng_raw)
            except ValueError:
                continue
            # 서울 위경도 대략 범위 체크
            if not (37.4 <= lat <= 37.7 and 126.7 <= lng <= 127.2):
                continue

            rows.append({
                "type": "park",
                "name": name,
                "lat": lat,
                "lng": lng,
                "district": district,
            })
    return rows

def read_libraries():
    """도서관 CSV → list of dict (공공도서관만)"""
    rows = []
    with open(LIBRARY_CSV, encoding="cp949") as f:
        reader = csv.DictReader(f)
        for r in reader:
            kind = r.get("도서관 구분명", "").strip()
            if kind != "공공도서관":
                continue  # 작은도서관·어린이도서관 등 제외

            district = r.get("구명", "").strip()
            if district not in SEOUL_DISTRICTS:
                continue

            name = clean_name(r.get("도서관명", ""))
            lat_raw = r.get("위도", "").strip()
            lng_raw = r.get("경도", "").strip()
            if not name or not lat_raw or not lng_raw:
                continue
            try:
                lat = float(lat_raw)
                lng = float(lng_raw)
            except ValueError:
                continue
            if not (37.4 <= lat <= 37.7 and 126.7 <= lng <= 127.2):
                continue

            rows.append({
                "type": "library",
                "name": name,
                "lat": round(lat, 6),  # 소수점 6자리면 충분 (오차 ~10cm)
                "lng": round(lng, 6),
                "district": district,
            })
    return rows

def main():
    parks = read_parks()
    libs  = read_libraries()
    all_places = parks + libs

    # 출력 디렉토리 생성
    os.makedirs(os.path.dirname(OUTPUT_CSV), exist_ok=True)

    with open(OUTPUT_CSV, "w", encoding="utf-8", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=["type", "name", "lat", "lng", "district"])
        writer.writeheader()
        writer.writerows(all_places)

    print(f"[OK] {OUTPUT_CSV}")
    print(f"  공원   : {len(parks)}개")
    print(f"  도서관 : {len(libs)}개")
    print(f"  총     : {len(all_places)}개")

    # 자치구별 분포 출력
    from collections import Counter
    by_district = Counter((r["district"], r["type"]) for r in all_places)
    print("\n--- 자치구별 분포 ---")
    for d in sorted(SEOUL_DISTRICTS):
        p = by_district.get((d, "park"), 0)
        l = by_district.get((d, "library"), 0)
        if p + l > 0:
            print(f"  {d}: 공원 {p}, 도서관 {l}")

if __name__ == "__main__":
    main()
