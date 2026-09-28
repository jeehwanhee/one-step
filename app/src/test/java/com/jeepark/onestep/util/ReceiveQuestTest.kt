package com.jeepark.onestep.util

import com.jeepark.onestep.data.model.Mood
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ReceiveQuestTest {

    private fun ratiosFor(mood: Mood) = getQuestDifficulty(
        isolation = 50.0,
        mood = mood,
        prevSuccess = 5,
        prevDiff = 3.0,
        tier = 2,
    )

    @Test
    fun `모든 기분에서 난이도 1부터 5까지 5칸의 비율이 나온다`() {
        Mood.entries.forEach { mood ->
            assertEquals("mood=$mood", 5, ratiosFor(mood).size)
        }
    }

    @Test
    fun `모든 기분에서 비율의 합은 1이고 음수가 없다`() {
        Mood.entries.forEach { mood ->
            val ratios = ratiosFor(mood)
            assertEquals("mood=$mood", 1.0, ratios.sum(), 1e-9)
            assertTrue("mood=$mood", ratios.all { it >= 0.0 })
        }
    }

    @Test
    fun `기분이 다르면 모델 입력이 달라져 결과도 달라질 수 있다`() {
        // 기분(level)이 모델 특성으로 실제로 전달되는지: 가장 나쁨과 가장 좋음의 결과가 같으면 입력이 무시된 것
        assertTrue(ratiosFor(Mood.VERY_BAD) != ratiosFor(Mood.VERY_GOOD))
    }
}
