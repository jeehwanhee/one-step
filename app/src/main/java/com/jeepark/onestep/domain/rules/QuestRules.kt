package com.jeepark.onestep.domain.rules

import com.jeepark.onestep.domain.model.DailyQuota
import com.jeepark.onestep.domain.model.PrevQuest
import com.jeepark.onestep.domain.model.PrevQuestFields
import com.jeepark.onestep.domain.model.Progression
import com.jeepark.onestep.domain.model.Quest
import com.jeepark.onestep.domain.model.User

// 퀘스트 관련 판단 규칙 (순수 함수, Firebase/Android 의존 없음).
// MainViewModel에서 규칙만 분리해 ViewModel을 얇게 유지하고 단독으로 테스트할 수 있게 한다.
// 규칙은 [User] 전체가 아니라 필요한 값만 담은 좁은 뷰([DailyQuota], [Progression])를 받는다(UserViews.kt).

const val DAILY_QUEST_LIMIT = 20
const val RECENT_QUEUE_SIZE = 10

/** 오늘 이미 일일 퀘스트 한도(20개)에 도달했는지. 날짜가 바뀌었으면 false. */
fun hasReachedDailyLimit(quota: DailyQuota, today: String): Boolean =
    quota.date == today && quota.count >= DAILY_QUEST_LIMIT

/** 퀘스트 완료 시 계산된 결과 묶음. 저장소에 넘길 값과 Firestore 저장용 완료 기록 Map을 제공한다. */
data class QuestCompletion(
    val newProgress: Int,
    val newTier: Int,
    val didTierUp: Boolean,
    val newDifficultyQueue: List<Double>,
    val newResultsQueue: List<Int>,
    val prevQuest: PrevQuest,
) {
    fun toPrevQuestMap(): Map<String, Any> = mapOf(
        PrevQuestFields.QUEST_NAME       to prevQuest.questName,
        PrevQuestFields.QUEST_EXP        to prevQuest.questEXP,
        PrevQuestFields.DIFFICULTY       to prevQuest.difficulty,
        PrevQuestFields.CONFIRM_QUESTION to prevQuest.confirmQuestion,
        PrevQuestFields.CONFIRM_ANSWER   to prevQuest.confirmAnswer,
        PrevQuestFields.DONE_DATE        to prevQuest.doneDate,
    )
}

/** 퀘스트 완료 후의 진행도·티어·최근 큐·완료 기록을 계산한다. */
fun computeQuestCompletion(
    progression: Progression,
    quest: Quest,
    answer: String,
    doneDate: String,
): QuestCompletion {
    val tierResult = calculateTierProgress(
        progress = progression.progress,
        tier     = progression.tier,
        exp      = quest.questEXP,
    )
    return QuestCompletion(
        newProgress        = tierResult.newProgress,
        newTier            = tierResult.newTier,
        didTierUp          = tierResult.didTierUp,
        newDifficultyQueue = (progression.difficultyQueue + quest.difficulty.toDouble()).takeLast(RECENT_QUEUE_SIZE),
        newResultsQueue    = (progression.resultsQueue + 1).takeLast(RECENT_QUEUE_SIZE),
        prevQuest          = PrevQuest(
            questName       = quest.questName,
            questEXP        = quest.questEXP,
            difficulty      = quest.difficulty,
            confirmQuestion = quest.confirmQuestion,
            confirmAnswer   = answer,
            doneDate        = doneDate,
        ),
    )
}

/** 퀘스트 포기 시 최근 결과 큐(성공 1 / 실패 0)에 실패(0)를 추가한 새 큐. */
fun giveUpResultsQueue(progression: Progression): List<Int> =
    (progression.resultsQueue + 0).takeLast(RECENT_QUEUE_SIZE)
