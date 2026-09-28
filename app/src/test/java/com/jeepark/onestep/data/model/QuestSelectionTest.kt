package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
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

    // ===== 난이도 비율 샘플링 =====

    @Test
    fun `난이도 비율대로 개수를 뽑는다`() {
        val ratios = listOf(0.1, 0.2, 0.4, 0.2, 0.1)

        val sampled = sampleByRatio(quests(), ratios, SAMPLE_SIZE, Random(1))

        assertEquals(listOf(2, 4, 8, 4, 2), countsByLevel(sampled))
        assertEquals(SAMPLE_SIZE, sampled.size)
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
}
