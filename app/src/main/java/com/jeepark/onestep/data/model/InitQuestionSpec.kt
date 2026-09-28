package com.jeepark.onestep.data.model

// 고립도 설문(초기 질문) 문항 정의 (순수 데이터·함수, Android 의존 없음).
// 화면이 입력받는 범위와 모델이 학습한 범위는 서로 다르다. 후자는 IsolationScorer.kt에 정리돼 있다.

/**
 * 설문 한 문항.
 * @param title 화면에 보이는 질문(줄바꿈 포함)
 * @param min 입력할 수 있는 최솟값
 * @param max 입력할 수 있는 최댓값
 */
data class InitQuestionSpec(val title: String, val min: Int, val max: Int) {
    /** 입력값을 이 문항의 범위 안으로 맞춘다. */
    fun coerce(value: Int): Int = value.coerceIn(min, max)
}

/**
 * 설문 문항 6개. 순서는 화면에 보이는 순서이자 [buildInitQuestions]가 답변을 필드에 대응시키는 순서다:
 * 식사 → 수면 → 샤워 → 외출 → 미취업 기간 → 활동 시간대.
 */
val INIT_QUESTIONS: List<InitQuestionSpec> = listOf(
    InitQuestionSpec("어제 식사 횟수", min = 0, max = 10),
    InitQuestionSpec("어제 수면 시간", min = 0, max = 24),
    InitQuestionSpec("지난 일주일 동안의\n샤워 횟수", min = 0, max = 7),
    InitQuestionSpec("지난 일주일 동안\n밖에 나간 일 수", min = 0, max = 7),
    InitQuestionSpec("일이나 학업을\n하지 않은 기간 (월)", min = 0, max = 600),
    InitQuestionSpec("주된 활동 시간\n0(새벽) 1(오전) 2(오후) 3(저녁)", min = 0, max = 3),
)

/** 문항 순서대로 모은 답변으로 저장용 [InitQuestions]를 만든다. 답변 개수가 문항 수와 다르면 예외. */
fun buildInitQuestions(answers: List<Int>): InitQuestions {
    require(answers.size == INIT_QUESTIONS.size) {
        "답변은 ${INIT_QUESTIONS.size}개여야 합니다 (받은 개수: ${answers.size})"
    }
    return InitQuestions(
        meal       = answers[0],
        sleepTime  = answers[1],
        shower     = answers[2],
        outside    = answers[3],
        hiki       = answers[4],
        activeTime = answers[5],
    )
}
