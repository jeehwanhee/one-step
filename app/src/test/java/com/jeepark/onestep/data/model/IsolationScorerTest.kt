package com.jeepark.onestep.data.model

import com.jeepark.onestep.util.Model_A
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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

    // ===== 외출 일수 → 외출 단계 =====

    @Test
    fun `외출 일수는 주 0회 0단계, 1~2회 1단계, 3~4회 2단계, 5~6회 3단계, 매일 4단계로 바뀐다`() {
        val expected = mapOf(0 to 0.0, 1 to 1.0, 2 to 1.0, 3 to 2.0, 4 to 2.0, 5 to 3.0, 6 to 3.0, 7 to 4.0)

        expected.forEach { (days, stage) ->
            assertEquals("days=$days", stage, scaleOutFreq(days), 0.0)
        }
    }

    @Test
    fun `범위 밖 외출 일수는 0단계로 처리한다`() {
        // 화면이 0~7만 받으므로 실제로는 도달하지 않는 방어 동작. 큰 값도 0단계가 되는 기존 동작을 그대로 둔다.
        assertEquals(0.0, scaleOutFreq(8), 0.0)
        assertEquals(0.0, scaleOutFreq(-1), 0.0)
    }

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
    fun `남자는 0, 여자는 1로 들어간다`() {
        assertEquals(0.0, isolationFeatures(30, Gender.MALE, answers())[1], 0.0)
        assertEquals(1.0, isolationFeatures(30, Gender.FEMALE, answers())[1], 0.0)
    }

    @Test
    fun `모델이 학습한 범위 아래의 값은 하한으로 잘린다`() {
        val features = isolationFeatures(
            age = 10, gender = Gender.MALE,
            answers = answers(meal = -1, sleepTime = 0, shower = -3, outside = 0, hiki = -5, activeTime = -1),
        )

        // 나이 19, 샤워 0, 활동 0, 미취업 0, 수면 2(하한이 0이 아님), 식사 0
        assertArrayEquals(doubleArrayOf(19.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, 0.0), features, 0.0)
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

    @Test
    fun `경계값은 그대로 유지된다`() {
        val low = isolationFeatures(
            age = 19, gender = Gender.FEMALE,
            answers = answers(meal = 0, sleepTime = 2, shower = 0, outside = 0, hiki = 0, activeTime = 0),
        )
        val high = isolationFeatures(
            age = 38, gender = Gender.FEMALE,
            answers = answers(meal = 4, sleepTime = 20, shower = 7, outside = 7, hiki = 240, activeTime = 3),
        )

        assertArrayEquals(doubleArrayOf(19.0, 1.0, 0.0, 0.0, 0.0, 0.0, 2.0, 0.0), low, 0.0)
        assertArrayEquals(doubleArrayOf(38.0, 1.0, 7.0, 4.0, 3.0, 240.0, 20.0, 4.0), high, 0.0)
    }

    @Test
    fun `모델 입력은 항상 8개다`() {
        assertEquals(8, isolationFeatures(25, Gender.MALE, answers()).size)
    }

    // ===== 점수 =====

    @Test
    fun `점수는 모델 예측값에 100을 곱해 소수점을 버린 정수다`() {
        val scores = listOf(0.0 to 0, 0.5 to 50, 0.999 to 99, 1.0 to 100, 0.006 to 0)

        scores.forEach { (prediction, expected) ->
            val score = isolationScore(25, Gender.MALE, answers()) { prediction }
            assertEquals("prediction=$prediction", expected, score)
        }
    }

    @Test
    fun `점수는 반올림이 아니라 절삭이라 부동소수점 오차가 있으면 1 작아진다`() {
        // 0.57 * 100 = 56.99999999999999 — 기존 동작(toInt 절삭)을 그대로 유지하고 있음을 고정한다.
        assertEquals(56, isolationScore(25, Gender.MALE, answers()) { 0.57 })
    }

    @Test
    fun `점수 계산은 만들어진 모델 입력 벡터를 그대로 모델에 넘긴다`() {
        var received: DoubleArray? = null
        val input = answers(meal = 3, sleepTime = 7, shower = 4, outside = 2, hiki = 12, activeTime = 2)

        isolationScore(25, Gender.FEMALE, input) { received = it; 0.5 }

        assertArrayEquals(
            isolationFeatures(25, Gender.FEMALE, input),
            requireNotNull(received) { "모델이 호출되지 않았다" },
            0.0,
        )
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

    @Test
    fun `가능한 입력 조합 전체에서 점수는 0에서 100 사이다`() {
        for (age in listOf(10, 25, 50)) for (gender in Gender.entries)
            for (meal in listOf(0, 4, 10)) for (sleep in listOf(0, 8, 24))
                for (outside in listOf(0, 3, 7)) for (hiki in listOf(0, 100, 600))
                    for (active in listOf(0, 3)) for (shower in listOf(0, 7)) {
                        val input = answers(meal, sleep, shower, outside, hiki, active)

                        val score = isolationScore(age, gender, input)

                        assertTrue("score=$score for age=$age $gender $input", score in 0..100)
                    }
    }
}
