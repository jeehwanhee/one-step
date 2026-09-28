package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class InitQuestionSpecTest {

    @Test
    fun `설문은 여섯 문항이고 순서는 화면에 보이던 그대로다`() {
        assertEquals(
            listOf(
                "어제 식사 횟수",
                "어제 수면 시간",
                "지난 일주일 동안의\n샤워 횟수",
                "지난 일주일 동안\n밖에 나간 일 수",
                "일이나 학업을\n하지 않은 기간 (월)",
                "주된 활동 시간\n0(새벽) 1(오전) 2(오후) 3(저녁)",
            ),
            INIT_QUESTIONS.map { it.title },
        )
    }

    @Test
    fun `문항별 입력 범위는 화면에서 받던 그대로다`() {
        assertEquals(
            listOf(0..10, 0..24, 0..7, 0..7, 0..600, 0..3),
            INIT_QUESTIONS.map { it.min..it.max },
        )
    }

    @Test
    fun `모든 문항은 최솟값이 최댓값보다 작거나 같다`() {
        assertTrue(INIT_QUESTIONS.all { it.min <= it.max })
    }

    @Test
    fun `입력값을 문항 범위로 맞춘다`() {
        val meal = INIT_QUESTIONS[0] // 0..10

        assertEquals(0, meal.coerce(0))
        assertEquals(7, meal.coerce(7))
        assertEquals(10, meal.coerce(10))
        assertEquals(10, meal.coerce(99))
        assertEquals(0, meal.coerce(-5))
    }

    @Test
    fun `답변 목록은 문항 순서대로 식사 수면 샤워 외출 미취업 활동 시간대에 대응한다`() {
        val questions = buildInitQuestions(listOf(1, 2, 3, 4, 5, 6))

        assertEquals(
            InitQuestions(meal = 1, sleepTime = 2, shower = 3, outside = 4, hiki = 5, activeTime = 6),
            questions,
        )
    }

    @Test
    fun `답변 개수가 문항 수와 다르면 만들 수 없다`() {
        val failure = runCatching { buildInitQuestions(listOf(1, 2, 3)) }.exceptionOrNull()

        assertTrue(failure is IllegalArgumentException)
    }
}
