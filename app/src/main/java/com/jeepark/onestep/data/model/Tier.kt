package com.jeepark.onestep.data.model

//티어별 경험치 통

val EXPAMOUNT = arrayOf(15, 48, 120, 230, 400, 650, 1000)

data class TierProgressResult(
    val newProgress: Int,
    val newTier: Int,
    val didTierUp: Boolean
)

/**
 * 경험치 획득 후 진행도·티어 계산 (순수 함수, Firebase/Android 의존 없음).
 * EXPAMOUNT[tier]는 그 티어에서 다음 티어로 넘어가는 데 필요한 경험치.
 * 최고 티어(EXPAMOUNT.size)에 도달하면 더는 오르지 않고 progress만 누적된다.
 */
fun calculateTierProgress(progress: Int, tier: Int, exp: Int): TierProgressResult {
    var newProgress = progress + exp
    var newTier = tier

    while (newTier < EXPAMOUNT.size) {
        val threshold = EXPAMOUNT[newTier]
        if (newProgress >= threshold) {
            newProgress -= threshold
            newTier++
        } else break
    }

    return TierProgressResult(
        newProgress = newProgress,
        newTier     = newTier,
        didTierUp   = newTier > tier
    )
}

