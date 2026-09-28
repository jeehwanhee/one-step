package com.jeepark.onestep.util

import com.jeepark.onestep.data.model.Animal
import com.jeepark.onestep.data.model.AnimalIds
import com.jeepark.onestep.data.model.AnimalRegistry
import com.jeepark.onestep.data.model.PrevQuest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.time.ZoneOffset

class AnimalUnlockDateTest {

    private fun animal(id: String): Animal = requireNotNull(AnimalRegistry.get(id)) { "등록되지 않은 동물: $id" }

    private val chick = animal(AnimalIds.CHICK)
    private val turtle = animal(AnimalIds.TURTLE)
    private val cat = animal(AnimalIds.CAT)

    @Test
    fun `병아리는 가입일이 없으면 null이다`() {
        // Arrange
        val quests = listOf(PrevQuest(questEXP = 100, doneDate = "2026.01.01 10:00:00"))
        // Act
        val result = findAnimalUnlockDate(animal = chick, prevQuests = quests)
        // Assert
        assertNull(result)
    }

    @Test
    fun `병아리는 퀘스트 기록 대신 가입일을 반환한다`() {
        // Arrange: 2026-03-05 00:00:00 UTC
        val joinDateMillis = 1772668800000L
        // Act
        val result = findAnimalUnlockDate(
            animal = chick, prevQuests = emptyList(), joinDateMillis = joinDateMillis, zone = ZoneOffset.UTC
        )
        // Assert
        assertEquals("2026.03.05", result)
    }

    @Test
    fun `가입일은 지정한 시간대의 날짜로 표시한다`() {
        // Arrange: 같은 순간이 UTC-5에서는 전날 19시
        val joinDateMillis = 1772668800000L
        // Act
        val result = findAnimalUnlockDate(
            animal = chick, prevQuests = emptyList(), joinDateMillis = joinDateMillis, zone = ZoneOffset.ofHours(-5)
        )
        // Assert
        assertEquals("2026.03.04", result)
    }

    @Test
    fun `필요한 경험치를 채운 퀘스트의 날짜를 반환한다`() {
        // Arrange: tier 0의 문턱은 15, 정확히 15 EXP짜리 퀘스트 하나로 티어 1(거북이) 해금
        val quests = listOf(PrevQuest(questEXP = 15, doneDate = "2026.01.01 10:00:00"))
        // Act
        val result = findAnimalUnlockDate(animal = turtle, prevQuests = quests)
        // Assert
        assertEquals("2026.01.01", result)
    }

    @Test
    fun `아직 해금되지 않은 동물은 null을 반환한다`() {
        // Arrange
        val quests = listOf(PrevQuest(questEXP = 5, doneDate = "2026.01.01 10:00:00"))
        // Act
        val result = findAnimalUnlockDate(animal = turtle, prevQuests = quests)
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
        val result = findAnimalUnlockDate(animal = turtle, prevQuests = quests)
        // Assert: earlier(01.01)로 progress=10, later(01.02)에서 20-15=5로 티어업 → 01.02가 해금일
        assertEquals("2026.01.02", result)
    }

    @Test
    fun `해금 티어가 2인 동물은 두 번째 티어업이 일어난 퀘스트의 날짜를 반환한다`() {
        // Arrange: tier 0 문턱 15 → tier 1 문턱 48. 01.01에 15(거북이 해금), 01.03에 48(고양이 해금)
        val quests = listOf(
            PrevQuest(questEXP = 15, doneDate = "2026.01.01 10:00:00"),
            PrevQuest(questEXP = 48, doneDate = "2026.01.03 10:00:00"),
        )
        // Act & Assert
        assertEquals("2026.01.01", findAnimalUnlockDate(turtle, quests))
        assertEquals("2026.01.03", findAnimalUnlockDate(cat, quests))
    }

    @Test
    fun `처음부터 있는 동물은 해금 티어가 0이라 퀘스트 기록이 있어도 가입일만 쓴다`() {
        val quests = listOf(PrevQuest(questEXP = 100, doneDate = "2026.01.01 10:00:00"))

        assertEquals(0, chick.unlockTier)
        assertEquals(
            "2026.03.05",
            findAnimalUnlockDate(chick, quests, joinDateMillis = 1772668800000L, zone = ZoneOffset.UTC),
        )
    }
}
