package com.jeepark.onestep.domain.model

/**
 * 퀘스트를 받을 때 사용자가 고르는 현재 기분. [level]은 난이도 모델(Model_B)의 입력 특성이라
 * 1(매우 나쁨)..5(매우 좋음) 범위를 유지해야 한다.
 */
enum class Mood(val level: Int) {
    VERY_BAD(1),
    BAD(2),
    NEUTRAL(3),
    GOOD(4),
    VERY_GOOD(5),
}
