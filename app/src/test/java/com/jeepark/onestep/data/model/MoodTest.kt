package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class MoodTest {

    @Test
    fun `기분 단계는 나쁨에서 좋음 순서로 1부터 5까지다`() {
        assertEquals(listOf(1, 2, 3, 4, 5), Mood.entries.map { it.level })
    }
}
