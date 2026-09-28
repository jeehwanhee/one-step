package com.jeepark.onestep.domain.model

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class IsolatedRecord(
    val score: Int = 0,
    val recordedAt: Long = System.currentTimeMillis(),
)

// 필드 이름은 [UserFields]와 같고 저장 형식이라 바꾸면 안 된다.
// `users` 문서에는 앱이 쓰기만 하는 통계용 필드 giveUpReasons가 있다([UserFields.GIVE_UP_REASONS]).
// 읽을 필요가 없어 모델에 두지 않고, @IgnoreExtraProperties로 모델에 없는 필드는 조용히 넘어간다.
@IgnoreExtraProperties
data class User(
    val uid: String = "",
    val email: String = "",
    val nickname: String = "",
    val age: Int = 0,
    val gender: Boolean =true, //true-남자
    val initQuestions: InitQuestions = InitQuestions(),

    val isolated: Int = 0,
    val isolatedLastModified: Long = System.currentTimeMillis(),
    /** 레거시(설문+퀘스트 완료를 함께 세던 옛 카운터). 더는 읽거나 쓰지 않고, 기존 문서와의 호환을 위해 필드만 남겨둔다. */
    val isolatedCount: Int = 0,
    val isolatedHistory: List<IsolatedRecord> = emptyList(),
    /** 마지막 설문 이후 완료한 퀘스트 수. 재설문 시점 판단에 쓴다(AssessmentRules.kt). 기존 사용자는 0에서 시작한다. */
    val questsSinceAssessment: Int = 0,

    val prevQuests: List<PrevQuest> = emptyList(),
    val difficultyQueue: List<Double> = emptyList(),  // 최근 10개 퀘스트 난이도
    val questResultsQueue: List<Int> = emptyList(),   // 최근 10개 성공(1)/실패(0)
    val tier: Int = 0,
    val progress: Int = 0,

    val lastAccessDate: Long = System.currentTimeMillis(), //최근 접속일
    val termAgreed: Boolean = true,
    val policyAgreed: Boolean = true,
    val agreedAt: Long = System.currentTimeMillis(),
    val notificationAgreed: Boolean = true,
    val dailyQuestCount: Int = 0,
    val dailyQuestDate: String = "",
)
