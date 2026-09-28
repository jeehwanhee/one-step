package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class QuestSelectionTest {

    /** 난이도 1~5마다 [perLevel]개씩, 이름이 모두 다른 퀘스트. */
    private fun quests(perLevel: Int = 10): List<Quest> =
        DIFFICULTY_LEVELS.flatMap { level ->
            (1..perLevel).map { n ->
                Quest(index = level * 100 + n, questName = "퀘스트 $level-$n", difficulty = level, questEXP = level * 5)
            }
        }

    private fun countsByLevel(selected: List<Quest>): List<Int> =
        DIFFICULTY_LEVELS.map { level -> selected.count { it.difficulty == level } }

    // ===== 상수 =====

    @Test
    fun `후보는 20개를 뽑고 최종으로 8개를 보여준다`() {
        assertEquals(20, SAMPLE_SIZE)
        assertEquals(8, SELECTION_SIZE)
        assertEquals(1..5, DIFFICULTY_LEVELS)
    }

    // ===== 난이도 비율 샘플링 =====

    @Test
    fun `난이도 비율대로 개수를 뽑는다`() {
        val ratios = listOf(0.1, 0.2, 0.4, 0.2, 0.1)

        val sampled = sampleByRatio(quests(), ratios, SAMPLE_SIZE, Random(1))

        assertEquals(listOf(2, 4, 8, 4, 2), countsByLevel(sampled))
        assertEquals(SAMPLE_SIZE, sampled.size)
    }

    @Test
    fun `한 난이도에 몰린 비율도 그대로 따른다`() {
        val sampled = sampleByRatio(quests(perLevel = 30), listOf(0.0, 0.0, 0.0, 0.0, 1.0), SAMPLE_SIZE, Random(1))

        assertEquals(listOf(0, 0, 0, 0, 20), countsByLevel(sampled))
    }

    @Test
    fun `뽑을 수 있는 개수가 모자라면 남은 퀘스트로 채워서 총 개수를 맞춘다`() {
        // 난이도 3 퀘스트는 2개뿐인데 8개를 요구 → 부족분 6개는 다른 난이도에서 채운다
        val pool = quests(perLevel = 10).filter { it.difficulty != 3 } +
            quests().filter { it.difficulty == 3 }.take(2)

        val sampled = sampleByRatio(pool, listOf(0.1, 0.2, 0.4, 0.2, 0.1), SAMPLE_SIZE, Random(1))

        assertEquals(SAMPLE_SIZE, sampled.size)
        assertEquals(2, sampled.count { it.difficulty == 3 })
    }

    @Test
    fun `퀘스트가 총 개수보다 적으면 있는 것을 전부 돌려준다`() {
        val few = quests(perLevel = 2) // 10개

        val sampled = sampleByRatio(few, listOf(0.2, 0.2, 0.2, 0.2, 0.2), SAMPLE_SIZE, Random(1))

        assertEquals(few.toSet(), sampled.toSet())
    }

    @Test
    fun `비율 목록이 짧으면 빠진 난이도는 0으로 본다`() {
        val sampled = sampleByRatio(quests(), listOf(0.5, 0.5), SAMPLE_SIZE, Random(1))

        assertEquals(listOf(10, 10, 0, 0, 0), countsByLevel(sampled))
    }

    @Test
    fun `비율의 합이 1을 넘어도 총 개수를 넘기지 않는다`() {
        val sampled = sampleByRatio(quests(), listOf(0.3, 0.3, 0.3, 0.3, 0.3), SAMPLE_SIZE, Random(1))

        assertEquals(SAMPLE_SIZE, sampled.size)
    }

    @Test
    fun `개수는 반올림한다`() {
        // 난이도 1: 0.125 × 20 = 2.5 → 3, 0.124 × 20 = 2.48 → 2. 두 난이도의 합이 총 개수 근처라 부족분 채우기가 끼지 않는다
        val pool = quests(perLevel = 20)
        val rounded = sampleByRatio(pool, listOf(0.125, 0.875, 0.0, 0.0, 0.0), 20, Random(1))
        val floored = sampleByRatio(pool, listOf(0.124, 0.876, 0.0, 0.0, 0.0), 20, Random(1))

        assertEquals(3, rounded.count { it.difficulty == 1 })
        assertEquals(2, floored.count { it.difficulty == 1 })
        assertEquals(18, floored.count { it.difficulty == 2 })
    }

    @Test
    fun `같은 퀘스트를 두 번 뽑지 않는다`() {
        val sampled = sampleByRatio(quests(), listOf(0.2, 0.2, 0.2, 0.2, 0.2), SAMPLE_SIZE, Random(3))

        assertEquals(sampled.size, sampled.toSet().size)
    }

    @Test
    fun `같은 난수 시드면 같은 결과이고 시드가 다르면 다른 결과가 나온다`() {
        val ratios = listOf(0.1, 0.2, 0.4, 0.2, 0.1)

        val a = sampleByRatio(quests(), ratios, SAMPLE_SIZE, Random(1))
        val b = sampleByRatio(quests(), ratios, SAMPLE_SIZE, Random(1))
        val c = sampleByRatio(quests(), ratios, SAMPLE_SIZE, Random(2))

        assertEquals(a, b)
        assertNotEquals(a, c)
    }

    @Test
    fun `빈 목록에서는 아무것도 뽑지 않는다`() {
        assertTrue(sampleByRatio(emptyList(), listOf(0.2, 0.2, 0.2, 0.2, 0.2), SAMPLE_SIZE, Random(1)).isEmpty())
    }

    // ===== 대체 선택 =====

    @Test
    fun `대체 선택은 후보에서 8개를 무작위로 고른다`() {
        val candidates = quests().take(SAMPLE_SIZE)

        val selected = randomSelection(candidates, Random(1))

        assertEquals(SELECTION_SIZE, selected.size)
        assertEquals(selected.size, selected.toSet().size)
        assertTrue(candidates.containsAll(selected))
    }

    @Test
    fun `후보가 8개보다 적으면 있는 것을 전부 돌려준다`() {
        val candidates = quests().take(3)

        assertEquals(candidates.toSet(), randomSelection(candidates, Random(1)).toSet())
    }

    @Test
    fun `대체 선택도 같은 시드면 같은 결과다`() {
        val candidates = quests().take(SAMPLE_SIZE)

        assertEquals(randomSelection(candidates, Random(9)), randomSelection(candidates, Random(9)))
    }
}
