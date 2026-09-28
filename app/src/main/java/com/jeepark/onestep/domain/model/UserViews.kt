package com.jeepark.onestep.domain.model

// 22개 필드를 가진 [User] 중 규칙 함수가 실제로 쓰는 값만 담은 좁은 뷰.
// 저장 구조는 [User] 그대로이고, 규칙 함수(QuestRules·AssessmentRules)가 필요한 것만 받게 해서
// "이 규칙이 사용자의 어떤 값에 의존하는가"가 시그니처에 드러나게 한다.
// 확장 함수라서 Firestore 모델 변환(set/toObject)에는 영향이 없다.

/** 하루 퀘스트 한도 계산에 쓰는 값: 마지막으로 센 날짜와 그날의 횟수. */
data class DailyQuota(val date: String, val count: Int)

/** 재설문 시점 판단에 쓰는 값. */
data class AssessmentState(val hasAssessed: Boolean, val questsSinceAssessment: Int)

/** 퀘스트를 완료·포기할 때 갱신되는 진행 상태: 티어·진행도와 최근 난이도·결과 큐. */
data class Progression(
    val tier: Int,
    val progress: Int,
    val difficultyQueue: List<Double>,
    val resultsQueue: List<Int>,
)

fun User.dailyQuota(): DailyQuota = DailyQuota(date = dailyQuestDate, count = dailyQuestCount)

fun User.withDailyQuota(quota: DailyQuota): User =
    copy(dailyQuestDate = quota.date, dailyQuestCount = quota.count)

fun User.assessmentState(): AssessmentState =
    AssessmentState(hasAssessed = isolatedHistory.isNotEmpty(), questsSinceAssessment = questsSinceAssessment)

fun User.progression(): Progression =
    Progression(tier = tier, progress = progress, difficultyQueue = difficultyQueue, resultsQueue = questResultsQueue)
