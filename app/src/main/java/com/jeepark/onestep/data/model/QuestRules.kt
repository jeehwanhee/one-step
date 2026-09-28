package com.jeepark.onestep.data.model

// 퀘스트 관련 판단 규칙 (순수 함수, Firebase/Android 의존 없음).
// MainViewModel에서 규칙만 분리해 ViewModel을 얇게 유지하고 단독으로 테스트할 수 있게 한다.

const val DAILY_QUEST_LIMIT = 20
const val RECENT_QUEUE_SIZE = 10

/** 오늘 이미 일일 퀘스트 한도(20개)에 도달했는지. 날짜가 바뀌었으면 false. */
fun hasReachedDailyLimit(user: User?, today: String): Boolean {
    if (user == null) return false
    return user.dailyQuestDate == today && user.dailyQuestCount >= DAILY_QUEST_LIMIT
}

/** 같은 날이면 카운트 +1, 날짜가 바뀌었으면 1로 리셋한 사용자를 반환. */
fun incrementedDailyCount(user: User, today: String): User =
    if (user.dailyQuestDate == today) {
        user.copy(dailyQuestCount = user.dailyQuestCount + 1)
    } else {
        user.copy(dailyQuestCount = 1, dailyQuestDate = today)
    }

/** 퀘스트 완료 시 계산된 결과 묶음. Firestore 저장용 Map과 로컬 사용자 갱신을 모두 제공한다. */
data class QuestCompletion(
    val newProgress: Int,
    val newTier: Int,
    val didTierUp: Boolean,
    val newDifficultyQueue: List<Double>,
    val newResultsQueue: List<Int>,
    val prevQuest: PrevQuest,
) {
    fun toPrevQuestMap(): Map<String, Any> = mapOf(
        "questName"       to prevQuest.questName,
        "questEXP"        to prevQuest.questEXP,
        "difficulty"      to prevQuest.difficulty,
        "confirmQuestion" to prevQuest.confirmQuestion,
        "confirmAnswer"   to prevQuest.confirmAnswer,
        "doneDate"        to prevQuest.doneDate,
    )

    fun applyTo(user: User): User = user.copy(
        progress          = newProgress,
        tier              = newTier,
        difficultyQueue   = newDifficultyQueue,
        questResultsQueue = newResultsQueue,
        prevQuests        = user.prevQuests + prevQuest,
        isolatedCount     = user.isolatedCount + 1,
    )
}

/**
 * 퀘스트 완료 후의 진행도·티어·최근 큐·완료 기록을 계산한다.
 * [doneDate]를 한 번만 받아 Firestore용 Map과 로컬 기록이 항상 같은 시각을 쓰도록 한다.
 */
fun computeQuestCompletion(
    user: User,
    quest: Quest,
    answer: String,
    doneDate: String,
): QuestCompletion {
    val tierResult = calculateTierProgress(
        progress = user.progress,
        tier     = user.tier,
        exp      = quest.questEXP,
    )
    return QuestCompletion(
        newProgress        = tierResult.newProgress,
        newTier            = tierResult.newTier,
        didTierUp          = tierResult.didTierUp,
        newDifficultyQueue = (user.difficultyQueue + quest.difficulty.toDouble()).takeLast(RECENT_QUEUE_SIZE),
        newResultsQueue    = (user.questResultsQueue + 1).takeLast(RECENT_QUEUE_SIZE),
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
fun giveUpResultsQueue(user: User): List<Int> =
    (user.questResultsQueue + 0).takeLast(RECENT_QUEUE_SIZE)
