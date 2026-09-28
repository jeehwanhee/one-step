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
    fun `모든 기분에서 비율의 합은 1이고 음수가 없다`() {
        Mood.entries.forEach { mood ->
            val ratios = ratiosFor(mood)
            assertEquals("mood=$mood", 1.0, ratios.sum(), 1e-9)
            assertTrue("mood=$mood", ratios.all { it >= 0.0 })
        }
    }
}
