package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.GeminiCandidate
import com.jeepark.onestep.data.model.GeminiContent
import com.jeepark.onestep.data.model.GeminiPart
import com.jeepark.onestep.data.model.GeminiResponse
import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.data.model.SELECTION_SIZE
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import kotlin.random.Random

class GeminiQuestRankerTest {

    private val quests = (0 until 20).map { Quest(index = it, questName = "퀘스트 $it", difficulty = it % 5 + 1) }

    private fun replyOf(text: String) =
        GeminiResponse(listOf(GeminiCandidate(GeminiContent(listOf(GeminiPart(text))))))

    private class Fixture {
        val service = FakeGeminiService()
        val ranker = GeminiQuestRanker(service, apiKey = "TEST_KEY", random = Random(1))
    }

    @Test
    fun `응답에 담긴 id 순서대로 퀘스트를 골라 돌려준다`() = runTest {
        val f = Fixture()
        f.service.response = replyOf("[4,0,9]")

        val result = f.ranker.select(quests, weather = "기온 3.0°C, 비")

        assertEquals(listOf(quests[4], quests[0], quests[9]), result)
    }

    @Test
    fun `API 키와 날씨가 든 프롬프트를 보낸다`() = runTest {
        val f = Fixture()
        f.service.response = replyOf("[1]")

        f.ranker.select(quests, weather = "기온 3.0°C, 비")

        assertEquals("TEST_KEY", f.service.lastApiKey)
        val prompt = requireNotNull(f.service.lastRequest).contents.single().parts.single().text
        assertTrue(prompt.contains("오늘 날씨: 기온 3.0°C, 비"))
        assertTrue(prompt.contains("퀘스트 0"))
    }

    @Test
    fun `응답에 후보가 없으면 무작위 8개로 대신한다`() = runTest {
        val f = Fixture()
        f.service.response = GeminiResponse(candidates = null)

        val result = f.ranker.select(quests, "정보 없음")

        assertEquals(SELECTION_SIZE, result.size)
        assertTrue(quests.containsAll(result))
    }

    @Test
    fun `응답 내용이 비어 있으면 무작위 8개로 대신한다`() = runTest {
        val f = Fixture()
        f.service.response = GeminiResponse(listOf(GeminiCandidate(GeminiContent(emptyList()))))

        assertEquals(SELECTION_SIZE, f.ranker.select(quests, "정보 없음").size)
    }

    @Test
    fun `응답을 읽을 수 없으면 무작위 8개로 대신한다`() = runTest {
        val f = Fixture()
        f.service.response = replyOf("죄송해요, 고르지 못했어요")

        val result = f.ranker.select(quests, "정보 없음")

        assertEquals(SELECTION_SIZE, result.size)
        assertTrue(quests.containsAll(result))
    }

    @Test
    fun `서버 오류는 삼키지 않고 호출한 쪽으로 던진다`() = runTest {
        val f = Fixture()
        val http = HttpException(Response.error<Any>(429, "quota exceeded".toResponseBody(null)))
        f.service.error = http

        val failure = runCatching { f.ranker.select(quests, "정보 없음") }.exceptionOrNull()

        assertSame(http, failure)
    }

    @Test
    fun `네트워크 오류도 그대로 던진다`() = runTest {
        val f = Fixture()
        f.service.error = java.io.IOException("연결 실패")

        val failure = runCatching { f.ranker.select(quests, "정보 없음") }.exceptionOrNull()

        assertNotNull(failure)
        assertEquals("연결 실패", failure?.message)
    }

    @Test
    fun `코루틴 취소도 그대로 전파한다`() = runTest {
        val f = Fixture()
        f.service.error = CancellationException("취소됨")

        val failure = runCatching { f.ranker.select(quests, "정보 없음") }.exceptionOrNull()

        assertTrue(failure is CancellationException)
    }
}
