package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class InitQuestionSpecTest {

    @Test
    fun `문항별 입력 범위는 화면에서 받던 그대로다`() {
        assertEquals(
            listOf(0..10, 0..24, 0..7, 0..7, 0..600, 0..3),
            INIT_QUESTIONS.map { it.min..it.max },
        )
    }

    @Test
    fun `답변 목록은 문항 순서대로 식사 수면 샤워 외출 미취업 활동 시간대에 대응한다`() {
        val questions = buildInitQuestions(listOf(1, 2, 3, 4, 5, 6))

        assertEquals(
            InitQuestions(meal = 1, sleepTime = 2, shower = 3, outside = 4, hiki = 5, activeTime = 6),
            questions,
        )
    }
}
