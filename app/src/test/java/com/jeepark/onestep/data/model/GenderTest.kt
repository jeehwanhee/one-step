package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class GenderTest {

    @Test
    fun `저장값은 기존 Firestore 형식대로 남자가 true, 여자가 false다`() {
        assertEquals(true, Gender.MALE.storedValue)
        assertEquals(false, Gender.FEMALE.storedValue)
    }
}
