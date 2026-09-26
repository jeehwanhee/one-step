package com.jeepark.onestep.util

import com.jeepark.onestep.data.model.PrevQuest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class AnimalUnlockDateTest {

    @Test
    fun `병아리는 가입일이 없으면 null이다`() {
        // Arrange
        val quests = listOf(PrevQuest(questEXP = 100, doneDate = "2026.01.01 10:00:00"))
        // Act
        val result = findAnimalUnlockDate(animalIndex = 0, prevQuests = quests)
        // Assert
        assertNull(result)
    }

    @Test
    fun `병아리는 퀘스트 기록 대신 가입일을 반환한다`() {
        // Arrange: 2026-03-05 00:00:00 UTC
        val joinDateMillis = 1772668800000L
        // Act
        val result = findAnimalUnlockDate(animalIndex = 0, prevQuests = emptyList(), joinDateMillis = joinDateMillis)
        // Assert
        assertEquals(java.text.SimpleDateFormat("yyyy.MM.dd", java.util.Locale.getDefault()).format(java.util.Date(joinDateMillis)), result)
    }

    @Test
    fun `필요한 경험치를 채운 퀘스트의 날짜를 반환한다`() {
        // Arrange: tier 0의 문턱은 15, 정확히 15 EXP짜리 퀘스트 하나로 티어 1(거북이) 해금
        val quests = listOf(PrevQuest(questEXP = 15, doneDate = "2026.01.01 10:00:00"))
        // Act
        val result = findAnimalUnlockDate(animalIndex = 1, prevQuests = quests)
        // Assert
        assertEquals("2026.01.01", result)
    }

    @Test
    fun `아직 해금되지 않은 동물은 null을 반환한다`() {
        // Arrange
        val quests = listOf(PrevQuest(questEXP = 5, doneDate = "2026.01.01 10:00:00"))
        // Act
        val result = findAnimalUnlockDate(animalIndex = 1, prevQuests = quests)
        // Assert
        assertNull(result)
    }

    @Test
    fun `입력 순서와 무관하게 doneDate 시간순으로 재생한다`() {
        // Arrange: 나중 날짜(01.02)가 리스트에서 먼저 오지만, 실제로는 01.01이 먼저 재생되어야 한다
        val later  = PrevQuest(questEXP = 10, doneDate = "2026.01.02 10:00:00")
        val earlier = PrevQuest(questEXP = 10, doneDate = "2026.01.01 10:00:00")
        val quests = listOf(later, earlier)
        // Act
        val result = findAnimalUnlockDate(animalIndex = 1, prevQuests = quests)
        // Assert: earlier(01.01)로 progress=10, later(01.02)에서 20-15=5로 티어업 → 01.02가 해금일
        assertEquals("2026.01.02", result)
    }
}
