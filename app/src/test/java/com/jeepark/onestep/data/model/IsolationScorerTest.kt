package com.jeepark.onestep.data.model

import com.jeepark.onestep.util.Model_A
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 고립도 산출의 특성화 테스트. 기대 벡터는 분리 전 `UserRepositoryImpl.saveInitQuestions`의 규칙
 * (클램프 범위, 특성 순서, 외출 일수 변환, 절삭)을 손으로 옮겨 적은 리터럴이라 구현과 독립적이다.
 * 특성 순서: [나이, 성별(남0/여1), 샤워, 외출 단계, 활동 시간대, 미취업 개월, 수면 시간, 식사 횟수]
 */
class IsolationScorerTest {

    private fun answers(
        meal: Int = 0, sleepTime: Int = 8, shower: Int = 0, outside: Int = 0, hiki: Int = 0, activeTime: Int = 0,
    ) = InitQuestions(shower = shower, meal = meal, sleepTime = sleepTime, outside = outside, hiki = hiki, activeTime = activeTime)

    // ===== 모델 입력 벡터 =====

    @Test
    fun `일반적인 입력은 범위를 벗어나지 않으면 그대로 순서대로 담긴다`() {
        val features = isolationFeatures(
            age = 25, gender = Gender.FEMALE,
            answers = answers(meal = 3, sleepTime = 7, shower = 4, outside = 2, hiki = 12, activeTime = 2),
        )

        assertArrayEquals(doubleArrayOf(25.0, 1.0, 4.0, 1.0, 2.0, 12.0, 7.0, 3.0), features, 0.0)
    }

    @Test
    fun `화면에서 입력 가능하지만 모델 범위를 넘는 값은 상한으로 잘린다`() {
        val features = isolationFeatures(
            age = 50, gender = Gender.MALE,
            answers = answers(meal = 10, sleepTime = 24, shower = 7, outside = 7, hiki = 600, activeTime = 3),
        )

        // 나이 38, 식사 4(화면은 10까지), 수면 20(화면은 24까지), 미취업 240(화면은 600까지), 외출 매일=4단계
        assertArrayEquals(doubleArrayOf(38.0, 0.0, 7.0, 4.0, 3.0, 240.0, 20.0, 4.0), features, 0.0)
    }

    // ===== 점수 =====

    @Test
    fun `점수는 반올림이 아니라 절삭이라 부동소수점 오차가 있으면 1 작아진다`() {
        // 0.57 * 100 = 56.99999999999999 — 기존 동작(toInt 절삭)을 그대로 유지하고 있음을 고정한다.
        assertEquals(56, isolationScore(25, Gender.MALE, answers()) { 0.57 })
    }

    // ===== 실제 모델(Model_A)과 연결 =====

    @Test
    fun `실제 모델로 계산한 점수는 손으로 만든 입력 벡터로 계산한 값과 같다`() {
        data class Case(val features: DoubleArray, val age: Int, val gender: Gender, val input: InitQuestions)

        val cases = listOf(
            Case(
                doubleArrayOf(25.0, 1.0, 4.0, 1.0, 2.0, 12.0, 7.0, 3.0), 25, Gender.FEMALE,
                answers(meal = 3, sleepTime = 7, shower = 4, outside = 2, hiki = 12, activeTime = 2),
            ),
            Case(
                doubleArrayOf(19.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, 0.0), 10, Gender.MALE,
                answers(meal = 0, sleepTime = 0, shower = 0, outside = 0, hiki = 0, activeTime = 0),
            ),
            Case(
                doubleArrayOf(38.0, 0.0, 7.0, 4.0, 3.0, 240.0, 20.0, 4.0), 50, Gender.MALE,
                answers(meal = 10, sleepTime = 24, shower = 7, outside = 7, hiki = 600, activeTime = 3),
            ),
            Case(
                doubleArrayOf(31.0, 1.0, 1.0, 2.0, 1.0, 60.0, 10.0, 2.0), 31, Gender.FEMALE,
                answers(meal = 2, sleepTime = 10, shower = 1, outside = 3, hiki = 60, activeTime = 1),
            ),
        )

        cases.forEach { case ->
            val expected = (Model_A.predict(case.features) * 100).toInt()

            assertEquals(expected, isolationScore(case.age, case.gender, case.input))
        }
    }
}
