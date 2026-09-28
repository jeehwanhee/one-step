package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.GeminiContent
import com.jeepark.onestep.data.model.GeminiPart
import com.jeepark.onestep.data.model.GeminiRequest
import com.jeepark.onestep.data.model.GeminiService
import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.data.model.buildRankingPrompt
import com.jeepark.onestep.data.model.parseRankedSelection
import com.jeepark.onestep.data.model.randomSelection
import kotlinx.coroutines.CancellationException
import kotlin.random.Random

/** 후보 퀘스트 중 오늘 할 만한 것을 골라 주는 서비스. 테스트에서는 FakeQuestRanker로 교체할 수 있다. */
interface QuestRanker {
    /** [quests] 중에서 [weather]에 맞는 퀘스트를 골라 돌려준다. 서비스에 닿지 못하면 예외를 던진다. */
    suspend fun select(quests: List<Quest>, weather: String): List<Quest>
}

/** Gemini 구현. 응답을 읽을 수 없으면 무작위 선택으로 대신하고, 네트워크 오류는 호출한 쪽이 처리하도록 던진다. */
class GeminiQuestRanker(
    private val service: GeminiService,
    private val apiKey: String,
    private val random: Random = Random.Default,
) : QuestRanker {

    override suspend fun select(quests: List<Quest>, weather: String): List<Quest> {
        val prompt = buildRankingPrompt(quests, weather)
        val response = try {
            service.generateContent(
                apiKey = apiKey,
                request = GeminiRequest(listOf(GeminiContent(listOf(GeminiPart(prompt))))),
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: retrofit2.HttpException) {
            // 어떤 quota인지 확인할 수 있도록 응답 본문도 남긴다
            val body = try { e.response()?.errorBody()?.string() } catch (_: Exception) { null }
            android.util.Log.e("GeminiRanker", "Gemini ${e.code()} body=$body")
            throw e
        }

        val rawText = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
            ?: return randomSelection(quests, random)
        return parseRankedSelection(rawText, quests, random)
    }
}
