package com.jeepark.onestep.data.model

import com.jeepark.onestep.util.PixelSprite

/**
 * 앱에 나오는 동물 한 마리의 모든 정보. 목록은 [AnimalRegistry]에 있다.
 * 동물은 순서(인덱스)가 아니라 고정 문자열 [id]로 식별한다.
 *
 * @param unlockTier 이 티어에 도달하면 해금된다.
 * @param isSky 공원에서 하늘 영역에 놓이는 동물인지(파랑새).
 * @param messages 공원에서 탭했을 때 말풍선에 나오는 대사 중 하나가 무작위로 뽑힌다.
 */
data class Animal(
    val id: String,
    val name: String,
    val unlockTier: Int,
    val isSky: Boolean,
    val sprite: PixelSprite,
    val messages: List<String>,
    val profile: AnimalProfile,
)

/** 진척도 화면의 동물 프로필 카드에 표시되는 정보. */
data class AnimalProfile(
    val personality: String,
    val likes: String,
    val habitat: String,
)
