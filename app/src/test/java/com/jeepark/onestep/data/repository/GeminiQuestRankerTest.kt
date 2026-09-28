package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.GeminiCandidate
import com.jeepark.onestep.data.model.GeminiContent
import com.jeepark.onestep.data.model.GeminiPart
import com.jeepark.onestep.data.model.GeminiResponse
import com.jeepark.onestep.data.model.Quest
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
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
    fun `서버 오류는 삼키지 않고 호출한 쪽으로 던진다`() = runTest {
        val f = Fixture()
        val http = HttpException(Response.error<Any>(429, "quota exceeded".toResponseBody(null)))
        f.service.error = http

        val failure = runCatching { f.ranker.select(quests, "정보 없음") }.exceptionOrNull()

        assertSame(http, failure)
    }
}
