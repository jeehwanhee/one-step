package com.jeepark.onestep.util

fun getQuestDifficulty(
    isolation: Double,    // 0: 고립도 (0~100)
    mood: Int,           // 1: 현재 기분 (1~5)
    prevSuccess: Int,    // 2: 최근 10개 중 성공 개수 (0~10)
    prevDiff: Double,    // 3: 최근 10개 평균 난이도 (1.0~5.0)
    tier: Int        // 4: 티어
): List<Double> {

    val inputData = doubleArrayOf(
        isolation,
        mood.toDouble(),
        prevSuccess.toDouble(),
        prevDiff,
        tier.toDouble() // 모델에 0.0 ~ 7.0으로 전달됨
    )

    // 모델 예측 및 안전 범위 제한 (1.0~5.0)
    val rawTarget = Model_B.predict(inputData)
    val target = rawTarget.coerceIn(1.0, 5.0)

    val ratios = MutableList(5) { 0.0 }
    val L = target.toInt()
    val d = target - L

    val pLower = 0.10
    val pMain = 0.80 - (0.25 * d)
    val pUpper = 0.10 + (0.25 * d)

    when (L) {
        1 -> {
            ratios[0] = pMain + pLower
            ratios[1] = pUpper
        }
        5 -> {
            ratios[3] = pLower
            ratios[4] = pMain + pUpper
        }
        else -> {
            ratios[L - 2] = pLower
            ratios[L - 1] = pMain
            ratios[L] = pUpper
        }
    }

    return ratios
}