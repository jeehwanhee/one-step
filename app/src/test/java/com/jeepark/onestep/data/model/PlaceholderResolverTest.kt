package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PlaceholderResolverTest {

    private fun quest(name: String, question: String = "무엇을 봤나요?", index: Int = 1) =
        Quest(index = index, questName = name, difficulty = 2, confirmQuestion = question, questEXP = 10)

    private fun place(name: String, type: String) = Place(type = type, name = name)

    // ===== 자리표시자 유무 =====

    @Test
    fun `이름이나 확인 질문에 자리표시자가 있으면 있다고 본다`() {
        assertTrue(hasPlaceholders(listOf(quest("{공원}에서 산책하기"))))
        assertTrue(hasPlaceholders(listOf(quest("산책하기", question = "{도서관}은 어땠나요?"))))
    }

    @Test
    fun `자리표시자가 없으면 없다고 본다`() {
        assertFalse(hasPlaceholders(listOf(quest("집 앞 걷기"), quest("창문 열기"))))
        assertFalse(hasPlaceholders(emptyList()))
    }

    @Test
    fun `여러 퀘스트 중 하나라도 있으면 있다고 본다`() {
        assertTrue(hasPlaceholders(listOf(quest("집 앞 걷기"), quest("{체육시설} 가보기"))))
    }

    // ===== 치환 =====

    @Test
    fun `이름과 확인 질문의 자리표시자를 장소 이름으로 바꾼다`() {
        val quests = listOf(quest("{공원}에서 산책하기", question = "{공원}에서 무엇을 봤나요?"))

        val result = resolvePlaceholders(quests) { type -> place("서울숲", type) }

        assertEquals("서울숲에서 산책하기", result.single().questName)
        assertEquals("서울숲에서 무엇을 봤나요?", result.single().confirmQuestion)
    }

    @Test
    fun `자리표시자마다 알맞은 종류의 장소를 찾는다`() {
        val requestedTypes = mutableListOf<String>()
        val quests = listOf(
            quest("{공원}에 가기"), quest("{도서관}에 가기"), quest("{청년공간}에 가기"),
            quest("{정신건강센터}에 가기"), quest("{체육시설}에 가기"),
        )

        resolvePlaceholders(quests) { type -> requestedTypes.add(type); place("이름", type) }

        assertEquals(listOf("park", "library", "youth_space", "mental_center", "gym"), requestedTypes)
    }

    @Test
    fun `한 퀘스트에 같은 자리표시자가 여러 번 있으면 모두 같은 이름으로 바뀐다`() {
        val quests = listOf(quest("{공원} 한 바퀴", question = "{공원} 어땠나요? {공원}에서 뭘 했나요?"))

        val result = resolvePlaceholders(quests) { type -> place("보라매공원", type) }

        assertEquals("보라매공원 한 바퀴", result.single().questName)
        assertEquals("보라매공원 어땠나요? 보라매공원에서 뭘 했나요?", result.single().confirmQuestion)
    }

    @Test
    fun `한 퀘스트에 서로 다른 자리표시자가 함께 있으면 각각 바뀐다`() {
        val quests = listOf(quest("{도서관}에서 책 빌리고 {공원}에서 읽기"))

        val result = resolvePlaceholders(quests) { type -> place(if (type == "park") "서울숲" else "구립도서관", type) }

        assertEquals("구립도서관에서 책 빌리고 서울숲에서 읽기", result.single().questName)
    }

    @Test
    fun `장소는 퀘스트마다 따로 골라서 같은 종류라도 퀘스트마다 다를 수 있다`() {
        val quests = listOf(quest("{공원}A", index = 1), quest("{공원}B", index = 2))
        var calls = 0

        val result = resolvePlaceholders(quests) { type -> place("공원${++calls}", type) }

        assertEquals(2, calls)
        assertEquals(listOf("공원1A", "공원2B"), result.map { it.questName })
    }

    @Test
    fun `장소를 찾지 못하면 종류별 대체 이름을 쓴다`() {
        val quests = listOf(
            quest("{공원}"), quest("{도서관}"), quest("{청년공간}"), quest("{정신건강센터}"), quest("{체육시설}"),
        )

        val result = resolvePlaceholders(quests) { null }

        assertEquals(
            listOf("근처 공원", "근처 도서관", "근처 청년공간", "가까운 정신건강복지센터", "근처 체육시설"),
            result.map { it.questName },
        )
    }

    @Test
    fun `자리표시자가 없는 퀘스트는 그대로 두고 장소를 찾지도 않는다`() {
        val quests = listOf(quest("집 앞 걷기"))
        var calls = 0

        val result = resolvePlaceholders(quests) { calls++; null }

        assertEquals(quests, result)
        assertEquals(0, calls)
    }

    @Test
    fun `치환해도 이름과 확인 질문 외의 필드는 그대로다`() {
        val original = Quest(index = 7, questName = "{공원} 걷기", difficulty = 4, confirmQuestion = "?", questEXP = 33)

        val result = resolvePlaceholders(listOf(original)) { type -> place("서울숲", type) }.single()

        assertEquals(original.copy(questName = "서울숲 걷기"), result)
    }

    @Test
    fun `알 수 없는 자리표시자는 손대지 않는다`() {
        val quests = listOf(quest("{카페}에 가기"))

        assertEquals("{카페}에 가기", resolvePlaceholders(quests) { null }.single().questName)
    }
}
