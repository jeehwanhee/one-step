package com.jeepark.onestep.ui.animal

import com.jeepark.onestep.domain.model.PrevQuest
import com.jeepark.onestep.domain.rules.QuestDate
import com.jeepark.onestep.domain.rules.calculateTierProgress
import java.time.ZoneId

/**
 * 동물의 "처음 만난 날"을 계산한다. 티어업 시각은 별도로 저장되지 않으므로,
 * 완료한 퀘스트를 시간순으로 재생하며 [animal]의 해금 티어를 처음 넘긴 날짜를 찾는다.
 * doneDate는 "yyyy.MM.dd HH:mm:ss" 고정폭 문자열이라 사전순 정렬이 곧 시간순 정렬이다.
 * 처음부터 있는 동물(해금 티어 0, 병아리)은 퀘스트 기록으로는 찾을 수 없으므로,
 * [joinDateMillis](가입일, User.agreedAt)가 주어지면 [zone] 기준의 그 날짜를 대신 반환한다.
 */
fun findAnimalUnlockDate(
    animal: Animal,
    prevQuests: List<PrevQuest>,
    joinDateMillis: Long? = null,
    zone: ZoneId = ZoneId.systemDefault()
): String? {
    val requiredTier = animal.unlockTier
    if (requiredTier <= 0) {
        return joinDateMillis?.let { QuestDate.formatDay(QuestDate.dayOf(it, zone)) }
    }

    var progress = 0
    var tier = 0
    prevQuests.sortedBy { it.doneDate }.forEach { quest ->
        val result = calculateTierProgress(progress, tier, quest.questEXP)
        progress = result.newProgress
        tier = result.newTier
        if (tier >= requiredTier) return QuestDate.dayPart(quest.doneDate)
    }
    return null
}
