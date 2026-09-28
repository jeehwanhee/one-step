package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class PlaceholderResolverTest {

    private fun quest(name: String, question: String = "무엇을 봤나요?", index: Int = 1) =
        Quest(index = index, questName = name, difficulty = 2, confirmQuestion = question, questEXP = 10)

    private fun place(name: String, type: String) = Place(type = type, name = name)

    // ===== 치환 =====

    @Test
    fun `이름과 확인 질문의 자리표시자를 장소 이름으로 바꾼다`() {
        val quests = listOf(quest("{공원}에서 산책하기", question = "{공원}에서 무엇을 봤나요?"))

        val result = resolvePlaceholders(quests) { type -> place("서울숲", type) }

        assertEquals("서울숲에서 산책하기", result.single().questName)
        assertEquals("서울숲에서 무엇을 봤나요?", result.single().confirmQuestion)
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
}
