package com.jeepark.onestep.data.model

import org.json.JSONArray
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class QuestRankingTest {

    private fun quests(count: Int = 20) = (0 until count).map { i ->
        Quest(index = 1000 + i, questName = "퀘스트 $i", difficulty = i % 5 + 1, questEXP = 10)
    }

    /** 프롬프트에 들어간 퀘스트 목록(JSON)을 다시 읽는다. */
    private fun questsInPrompt(prompt: String): JSONArray {
        val json = prompt.substringAfter("퀘스트 목록(JSON):\n").substringBefore("\n\n응답 형식")
        return JSONArray(json)
    }

    // ===== 프롬프트 =====

    @Test
    fun `프롬프트에 날씨와 선택할 개수가 들어간다`() {
        val prompt = buildRankingPrompt(quests(), weather = "기온 3.5°C, 비")

        assertTrue(prompt.contains("오늘 날씨: 기온 3.5°C, 비"))
        assertTrue(prompt.contains("퀘스트 ${SELECTION_SIZE}개를 골라줘"))
    }

    @Test
    fun `프롬프트의 퀘스트 목록은 위치를 id로 하는 올바른 JSON이다`() {
        val list = quests(3)

        val array = questsInPrompt(buildRankingPrompt(list, "정보 없음"))

        assertEquals(3, array.length())
        list.forEachIndexed { i, quest ->
            val item = array.getJSONObject(i)
            assertEquals(i, item.getInt("id"))
            assertEquals(quest.index, item.getInt("index"))
            assertEquals(quest.questName, item.getString("name"))
            assertEquals(quest.difficulty, item.getInt("difficulty"))
        }
    }

    @Test
    fun `이름에 따옴표 역슬래시 줄바꿈이 있어도 JSON이 깨지지 않는다`() {
        val tricky = Quest(index = 1, questName = "\"큰따옴표\" \\역슬래시\\ 첫줄\n둘째줄", difficulty = 2)

        val array = questsInPrompt(buildRankingPrompt(listOf(tricky), "정보 없음"))

        assertEquals(tricky.questName, array.getJSONObject(0).getString("name"))
    }

    @Test
    fun `퀘스트가 없어도 프롬프트를 만들 수 있다`() {
        val array = questsInPrompt(buildRankingPrompt(emptyList(), "정보 없음"))

        assertEquals(0, array.length())
    }

    @Test
    fun `프롬프트는 응답 형식으로 id 숫자 배열만 요구한다`() {
        val prompt = buildRankingPrompt(quests(), "정보 없음")

        assertTrue(prompt.contains("id 숫자만 JSON 배열로 반환"))
        assertTrue(prompt.contains("마크다운 없이 JSON 배열만"))
    }

    // ===== 응답 해석 =====

    @Test
    fun `응답의 id 순서대로 퀘스트를 돌려준다`() {
        val list = quests()

        val result = parseRankedSelection("[5,0,12]", list, Random(1))

        assertEquals(listOf(list[5], list[0], list[12]), result)
    }

    @Test
    fun `마크다운 코드 블록으로 감싸여 있어도 읽는다`() {
        val list = quests()

        assertEquals(listOf(list[1], list[2]), parseRankedSelection("```json\n[1,2]\n```", list, Random(1)))
        assertEquals(listOf(list[3]), parseRankedSelection("```\n[3]\n```", list, Random(1)))
        assertEquals(listOf(list[4]), parseRankedSelection("  \n [4] \n ", list, Random(1)))
    }

    @Test
    fun `범위 밖이거나 음수인 id는 무시한다`() {
        val list = quests(5)

        val result = parseRankedSelection("[0,99,-1,3]", list, Random(1))

        assertEquals(listOf(list[0], list[3]), result)
    }

    @Test
    fun `8개보다 많이 골라도 앞의 8개만 쓴다`() {
        val list = quests(20)

        val result = parseRankedSelection("[0,1,2,3,4,5,6,7,8,9,10,11]", list, Random(1))

        assertEquals(list.take(SELECTION_SIZE), result)
    }

    @Test
    fun `유효한 id가 하나도 없으면 무작위 선택으로 대신한다`() {
        val list = quests(20)

        val result = parseRankedSelection("[100,200]", list, Random(1))

        assertEquals(SELECTION_SIZE, result.size)
        assertTrue(list.containsAll(result))
    }

    @Test
    fun `빈 배열이면 무작위 선택으로 대신한다`() {
        val result = parseRankedSelection("[]", quests(20), Random(1))

        assertEquals(SELECTION_SIZE, result.size)
    }

    @Test
    fun `JSON이 아니거나 숫자가 아닌 값이 섞여 있으면 무작위 선택으로 대신한다`() {
        val list = quests(20)

        listOf("골라봤어요!", "", "{\"ids\":[1,2]}", "[\"a\",\"b\"]", "[1,").forEach { raw ->
            val result = parseRankedSelection(raw, list, Random(1))
            assertEquals("raw=$raw", SELECTION_SIZE, result.size)
            assertTrue("raw=$raw", list.containsAll(result))
        }
    }

    @Test
    fun `무작위 대신 선택도 같은 시드면 같은 결과다`() {
        val list = quests(20)

        assertEquals(
            parseRankedSelection("잘못된 응답", list, Random(7)),
            parseRankedSelection("잘못된 응답", list, Random(7)),
        )
    }
}
