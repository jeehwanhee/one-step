package com.jeepark.onestep.util

import com.jeepark.onestep.data.model.PrevQuest
import com.jeepark.onestep.data.model.calculateTierProgress
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * 동물의 "처음 만난 날"을 계산한다. 티어업 시각은 별도로 저장되지 않으므로,
 * 완료한 퀘스트를 시간순으로 재생하며 해당 동물의 해금 티어(=animalIndex)를 처음 넘긴 날짜를 찾는다.
 * doneDate는 "yyyy.MM.dd HH:mm:ss" 고정폭 문자열이라 사전순 정렬이 곧 시간순 정렬이다.
 * 병아리(인덱스 0)는 티어 0부터 있어 퀘스트 기록으로는 찾을 수 없으므로,
 * [joinDateMillis](가입일, User.agreedAt)가 주어지면 그 날짜를 대신 반환한다.
 */
fun findAnimalUnlockDate(
    animalIndex: Int,
    prevQuests: List<PrevQuest>,
    joinDateMillis: Long? = null
): String? {
    val requiredTier = animalIndex
    if (requiredTier <= 0) {
        return joinDateMillis?.let {
            SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(Date(it))
        }
    }

    var progress = 0
    var tier = 0
    prevQuests.sortedBy { it.doneDate }.forEach { quest ->
        val result = calculateTierProgress(progress, tier, quest.questEXP)
        progress = result.newProgress
        tier = result.newTier
        if (tier >= requiredTier) return quest.doneDate.take(10)
    }
    return null
}
