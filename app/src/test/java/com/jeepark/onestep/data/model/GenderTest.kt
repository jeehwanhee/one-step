package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class GenderTest {

    @Test
    fun `저장값은 기존 Firestore 형식대로 남자가 true, 여자가 false다`() {
        assertEquals(true, Gender.MALE.storedValue)
        assertEquals(false, Gender.FEMALE.storedValue)
    }

    @Test
    fun `저장값에서 성별을 복원한다`() {
        assertEquals(Gender.MALE, Gender.fromStored(true))
        assertEquals(Gender.FEMALE, Gender.fromStored(false))
    }

    @Test
    fun `모든 성별은 저장값으로 바꿨다가 되돌려도 같다`() {
        Gender.entries.forEach { gender ->
            assertEquals(gender, Gender.fromStored(gender.storedValue))
        }
    }

    @Test
    fun `User의 기본 성별은 남자로 저장돼 있다`() {
        assertEquals(Gender.MALE, Gender.fromStored(User().gender))
    }
}
