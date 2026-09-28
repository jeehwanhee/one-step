package com.jeepark.onestep.ui.animal

import com.jeepark.onestep.domain.model.PrevQuest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.ZoneOffset

class AnimalUnlockDateTest {

    private fun animal(id: String): Animal = requireNotNull(AnimalRegistry.get(id)) { "등록되지 않은 동물: $id" }

    private val chick = animal(AnimalIds.CHICK)
    private val turtle = animal(AnimalIds.TURTLE)

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
    fun `필요한 경험치를 채운 퀘스트의 날짜를 반환한다`() {
        // Arrange: tier 0의 문턱은 15, 정확히 15 EXP짜리 퀘스트 하나로 티어 1(거북이) 해금
        val quests = listOf(PrevQuest(questEXP = 15, doneDate = "2026.01.01 10:00:00"))
        // Act
        val result = findAnimalUnlockDate(animal = turtle, prevQuests = quests)
        // Assert
        assertEquals("2026.01.01", result)
    }
}
