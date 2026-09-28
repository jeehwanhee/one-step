package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class GiveUpReasonTest {

    @Test
    fun `저장되는 코드값은 기존 Firestore 데이터와 같은 1, 2, 3이다`() {
        assertEquals(1, GiveUpReason.TOO_HARD.code)
        assertEquals(2, GiveUpReason.BAD_SITUATION.code)
        assertEquals(3, GiveUpReason.LOW_CONDITION.code)
    }
}
