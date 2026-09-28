package com.jeepark.onestep.data.model

import com.jeepark.onestep.util.BEAR_SPRITE
import com.jeepark.onestep.util.BLUEBIRD_SPRITE
import com.jeepark.onestep.util.CAT_SPRITE
import com.jeepark.onestep.util.CHICK_SPRITE
import com.jeepark.onestep.util.DOG_SPRITE
import com.jeepark.onestep.util.DOLPHIN_SPRITE
import com.jeepark.onestep.util.HORSE_SPRITE
import com.jeepark.onestep.util.TURTLE_SPRITE

// 동물 정보(이름·해금 티어·대사·프로필)를 한 곳에서 관리한다.
//
// ★ 새 동물을 추가하려면 ★
//   1. util/PixelAnimals.kt에 스프라이트(X_COLORS / X_PIXELS / X_SPRITE)를 만든다.
//   2. 아래 AnimalIds에 id를 추가하고 AnimalRegistry.all에 등록한다.
//   3. ui/screens/ParkLayouts.kt의 모든 배치(variant)에 위치를 추가한다.
// 2·3 중 하나라도 빠지면 AnimalRegistryTest / ParkLayoutsTest가 실패한다.

/** 동물의 고정 식별자. 저장하거나 화면 사이에 넘길 때 인덱스 대신 이 값을 쓴다. */
object AnimalIds {
    const val CHICK = "chick"
    const val TURTLE = "turtle"
    const val CAT = "cat"
    const val DOG = "dog"
    const val BLUEBIRD = "bluebird"
    const val BEAR = "bear"
    const val HORSE = "horse"
    const val DOLPHIN = "dolphin"
}

object AnimalRegistry {

    /** 전체 동물. 공원에서 그리는 순서이자 탭 판정 순서이기도 하다. */
    val all: List<Animal> = listOf(
        Animal(
            id = AnimalIds.CHICK, name = "병아리", unlockTier = 0, isSky = false, sprite = CHICK_SPRITE,
            messages = listOf(
                "오늘도 만나서 너무 좋아!",
                "어디 있다 왔어?",
                "나랑 친구할래?",
                "보고 싶었어",
                "오늘은 뭐 하고 놀까?",
            ),
            profile = AnimalProfile(personality = "호기심 많음", likes = "따뜻한 햇볕", habitat = "햇볕 잘 드는 언덕 위"),
        ),
        Animal(
            id = AnimalIds.TURTLE, name = "거북이", unlockTier = 1, isSky = false, sprite = TURTLE_SPRITE,
            messages = listOf(
                "조금 천천히 와도 돼",
                "오늘은 살짝 졸린 날이야",
                "쉬어가도 괜찮은걸",
                "급할 거 하나도 없어",
                "햇볕이 참 따뜻하지?",
            ),
            profile = AnimalProfile(personality = "느긋함", likes = "고요한 오후", habitat = "나무 그늘 아래"),
        ),
        Animal(
            id = AnimalIds.CAT, name = "고양이", unlockTier = 2, isSky = false, sprite = CAT_SPRITE,
            messages = listOf(
                "흥, 왔어?",
                "잠깐만 더 잘게",
                "옆에 있어줄래?",
                "별로 안 기다렸어",
                "쓰다듬어줘도 좋아",
            ),
            profile = AnimalProfile(personality = "도도함", likes = "따뜻한 담요", habitat = "나무 위"),
        ),
        Animal(
            id = AnimalIds.DOG, name = "강아지", unlockTier = 3, isSky = false, sprite = DOG_SPRITE,
            messages = listOf(
                "기다리고 있었어!",
                "나랑 산책 갈래?",
                "너만 보면 기분이 좋아져",
                "꼬리 멈출 수가 없어",
                "같이 있으면 행복해",
            ),
            profile = AnimalProfile(personality = "다정함", likes = "함께하는 산책", habitat = "마당 한켠"),
        ),
        Animal(
            id = AnimalIds.BLUEBIRD, name = "파랑새", unlockTier = 4, isSky = true, sprite = BLUEBIRD_SPRITE,
            messages = listOf(
                "하늘 좀 봐, 예쁘지?",
                "오늘은 어디 가볼까?",
                "바람이 정말 좋아",
                "위에서 내려다보면 다 작아",
                "멀리 가도 길을 잃지 않아",
            ),
            profile = AnimalProfile(personality = "자유로움", likes = "높은 하늘", habitat = "나뭇가지 위"),
        ),
        Animal(
            id = AnimalIds.BEAR, name = "곰", unlockTier = 5, isSky = false, sprite = BEAR_SPRITE,
            messages = listOf(
                "안아줄까?",
                "오늘 많이 힘들었지?",
                "옆에 있어줄게",
                "푹 쉬어도 괜찮아",
                "내가 든든하게 있어줄게",
            ),
            profile = AnimalProfile(personality = "포근함", likes = "든든한 포옹", habitat = "숲 속 그늘"),
        ),
        Animal(
            id = AnimalIds.HORSE, name = "말", unlockTier = 6, isSky = false, sprite = HORSE_SPRITE,
            messages = listOf(
                "함께 달려볼래?",
                "내가 너를 데려다줄게",
                "어디든 갈 수 있어",
                "네 속도에 맞춰줄게",
                "내 등에 타도 돼",
            ),
            profile = AnimalProfile(personality = "씩씩함", likes = "넓은 들판", habitat = "탁 트인 초원"),
        ),
        Animal(
            id = AnimalIds.DOLPHIN, name = "돌고래", unlockTier = 7, isSky = false, sprite = DOLPHIN_SPRITE,
            messages = listOf(
                "오늘 기분 좋아!",
                "수영하러 갈래?",
                "물속이 시원해",
                "같이 놀자!",
                "더 멀리 헤엄칠 수 있어",
            ),
            profile = AnimalProfile(personality = "명랑함", likes = "시원한 물살", habitat = "작은 연못"),
        ),
    )

    private val byId: Map<String, Animal> = all.associateBy { it.id }

    /** [id]에 해당하는 동물. 없으면 null. */
    fun get(id: String): Animal? = byId[id]

    /** [tier]에 도달한 사용자가 해금한 동물들 (레지스트리 순서). */
    fun unlockedAt(tier: Int): List<Animal> = all.filter { tier >= it.unlockTier }

    /** [tier]에서 가장 최근에 해금된 동물. 해금 티어가 같으면 레지스트리에서 뒤에 있는 동물. */
    fun latestUnlocked(tier: Int): Animal? = unlockedAt(tier).sortedBy { it.unlockTier }.lastOrNull()
}
