package com.jeepark.onestep.domain.rules

import com.jeepark.onestep.domain.model.AssessmentState

// 고립도 설문(재설문) 시점 규칙 (순수 함수, Firebase/Android 의존 없음).
// 앱 시작(InitScreen)과 앱 사용 중(MainScreen)이 같은 규칙 하나만 쓴다.

/** 마지막 설문 이후 이만큼 퀘스트를 완료하면 다시 설문한다. */
const val REASSESSMENT_INTERVAL_QUESTS = 10

/**
 * 고립도 설문이 필요한지.
 * 한 번도 설문하지 않았거나(이력 없음), 마지막 설문 이후 퀘스트를 [REASSESSMENT_INTERVAL_QUESTS]개
 * 이상 완료했으면 true. 설문을 제출하면 `questsSinceAssessment`가 0으로 돌아간다.
 */
fun needsAssessment(state: AssessmentState): Boolean =
    !state.hasAssessed || state.questsSinceAssessment >= REASSESSMENT_INTERVAL_QUESTS
