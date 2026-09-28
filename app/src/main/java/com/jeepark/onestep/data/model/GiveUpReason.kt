package com.jeepark.onestep.data.model

/**
 * 퀘스트 포기 사유. [code]는 Firestore(`users.giveUpReasons[].reason`, `quests.giveUpReasons[]`)에
 * 저장되는 값이므로 이미 쌓인 데이터와 통계에 영향을 주지 않으려면 절대 바꾸지 않는다.
 */
enum class GiveUpReason(val code: Int) {
    TOO_HARD(1),
    BAD_SITUATION(2),
    LOW_CONDITION(3),
}
