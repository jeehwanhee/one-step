package com.jeepark.onestep.util

// 동물별 대사(공원 화면 말풍선)와 프로필 정보(진척도 화면)를 한 곳에서 관리.
// 인덱스는 ANIMAL_NAMES와 동일: 0=병아리 1=거북이 2=고양이 3=강아지 4=파랑새 5=곰 6=말 7=돌고래

val animalMessages: Map<Int, List<String>> = mapOf(
    0 to listOf(
        "오늘도 만나서 너무 좋아!",
        "어디 있다 왔어?",
        "나랑 친구할래?",
        "보고 싶었어",
        "오늘은 뭐 하고 놀까?",
    ),
    1 to listOf(
        "조금 천천히 와도 돼",
        "오늘은 살짝 졸린 날이야",
        "쉬어가도 괜찮은걸",
        "급할 거 하나도 없어",
        "햇볕이 참 따뜻하지?",
    ),
    2 to listOf(
        "흥, 왔어?",
        "잠깐만 더 잘게",
        "옆에 있어줄래?",
        "별로 안 기다렸어",
        "쓰다듬어줘도 좋아",
    ),
    3 to listOf(
        "기다리고 있었어!",
        "나랑 산책 갈래?",
        "너만 보면 기분이 좋아져",
        "꼬리 멈출 수가 없어",
        "같이 있으면 행복해",
    ),
    4 to listOf(
        "하늘 좀 봐, 예쁘지?",
        "오늘은 어디 가볼까?",
        "바람이 정말 좋아",
        "위에서 내려다보면 다 작아",
        "멀리 가도 길을 잃지 않아",
    ),
    5 to listOf(
        "안아줄까?",
        "오늘 많이 힘들었지?",
        "옆에 있어줄게",
        "푹 쉬어도 괜찮아",
        "내가 든든하게 있어줄게",
    ),
    6 to listOf(
        "함께 달려볼래?",
        "내가 너를 데려다줄게",
        "어디든 갈 수 있어",
        "네 속도에 맞춰줄게",
        "내 등에 타도 돼",
    ),
    7 to listOf(
        "오늘 기분 좋아!",
        "수영하러 갈래?",
        "물속이 시원해",
        "같이 놀자!",
        "더 멀리 헤엄칠 수 있어",
    ),
)

data class AnimalProfileInfo(
    val personality: String,
    val likes: String,
    val habitat: String
)

// 성격은 동물마다 하나로 고정, 사는 곳은 메인 화면 속 실제 배치 컨셉을 반영한 고정 값.
val animalProfiles: Map<Int, AnimalProfileInfo> = mapOf(
    0 to AnimalProfileInfo(personality = "호기심 많음", likes = "따뜻한 햇볕",   habitat = "햇볕 잘 드는 언덕 위"),
    1 to AnimalProfileInfo(personality = "느긋함",     likes = "고요한 오후",   habitat = "나무 그늘 아래"),
    2 to AnimalProfileInfo(personality = "도도함",     likes = "따뜻한 담요",   habitat = "나무 위"),
    3 to AnimalProfileInfo(personality = "다정함",     likes = "함께하는 산책", habitat = "마당 한켠"),
    4 to AnimalProfileInfo(personality = "자유로움",   likes = "높은 하늘",     habitat = "나뭇가지 위"),
    5 to AnimalProfileInfo(personality = "포근함",     likes = "든든한 포옹",   habitat = "숲 속 그늘"),
    6 to AnimalProfileInfo(personality = "씩씩함",     likes = "넓은 들판",     habitat = "탁 트인 초원"),
    7 to AnimalProfileInfo(personality = "명랑함",     likes = "시원한 물살",   habitat = "작은 연못"),
)
