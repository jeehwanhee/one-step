package com.jeepark.onestep.util

import com.google.firebase.Firebase
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore
import com.jeepark.onestep.BuildConfig
import com.jeepark.onestep.data.model.GeminiClient
import com.jeepark.onestep.data.model.GeminiContent
import com.jeepark.onestep.data.model.GeminiPart
import com.jeepark.onestep.data.model.GeminiRequest
import com.jeepark.onestep.data.model.NetworkClient
import com.jeepark.onestep.data.model.Quest
import kotlinx.coroutines.tasks.await
import org.json.JSONArray
import kotlin.math.roundToInt
import com.jeepark.onestep.util.LocationHelper

class QuestRepository {
    private val db = Firebase.firestore

    suspend fun fetchFilteredQuests(
        ratios: List<Double>
    ): List<Quest> {
        // 1. Firestore quests 컬렉션 전체 로드
        val snapshot = try {
            db.collection("quests").get(Source.SERVER).await()
        } catch (e: Exception) {
            android.util.Log.w("QuestRepo", "서버 접근 실패, 캐시 사용: ${e.message}")
            db.collection("quests").get(Source.CACHE).await()
        }
        android.util.Log.d("QuestRepo", "문서 수: ${snapshot.documents.size}")
        snapshot.documents.take(3).forEach { doc ->
            android.util.Log.d("QuestRepo", "문서[${doc.id}] 필드: ${doc.data}")
        }
        val allQuests = snapshot.documents.mapNotNull { doc ->
            try {
                doc.toObject(Quest::class.java)
            } catch (e: Exception) {
                android.util.Log.e("QuestRepo", "역직렬화 실패 [${doc.id}]: ${e.message}")
                null
            }
        }.filter { it.questName.isNotEmpty() }
        android.util.Log.d("QuestRepo", "역직렬화 성공: ${allQuests.size}개")
        if (allQuests.isEmpty()) throw Exception("quests 컬렉션이 비어 있습니다 (문서 수: ${snapshot.documents.size})")

        // 2. 비율에 맞게 20개 샘플링
        val sampled = sampleByRatio(allQuests, ratios, 20)

        // 3. 날씨 + 혼잡도 조회
        val (weather, congestion) = fetchSeoulData()

        // 4. Gemini로 8개 선별 (실패 시 랜덤 8개 반환)
        return try {
            selectWithGemini(sampled, weather, congestion)
        } catch (e: Exception) {
            sampled.shuffled().take(8)
        }
    }

    private fun sampleByRatio(all: List<Quest>, ratios: List<Double>, total: Int): List<Quest> {
        val byLevel = (1..5).associateWith { level ->
            all.filter { it.difficulty == level }.shuffled()
        }
        val result = mutableListOf<Quest>()

        for (level in 1..5) {
            val count = (ratios.getOrElse(level - 1) { 0.0 } * total).roundToInt()
            result.addAll((byLevel[level] ?: emptyList()).take(count))
        }

        // 부족하면 남은 퀘스트로 채움
        if (result.size < total) {
            val extras = all.filterNot { it in result }.shuffled()
            result.addAll(extras.take(total - result.size))
        }

        return result.take(total)
    }

    private suspend fun fetchSeoulData(): Pair<String, String> = try {
        val response = NetworkClient.apiService.getRealtimeCityData(
            apiKey = BuildConfig.SEOUL_API_KEY,
            areaName = LocationHelper.currentAreaName
        )
        val w = response.CITYDATA?.WEATHER_STTS?.firstOrNull()
        val p = response.CITYDATA?.LIVE_PPLTN_STTS?.firstOrNull()
        val weather = if (w != null)
            "${w.WEATHER_MSG}, 기온 ${w.TEMP}°C, 강수 ${w.PCP_MSG}, 미세먼지 ${w.PM10}"
        else "정보 없음"
        val congestion = p?.AREA_CONGEST_LVL ?: "정보 없음"
        Pair(weather, congestion)
    } catch (e: Exception) {
        Pair("정보 없음", "정보 없음")
    }

    private suspend fun selectWithGemini(
        quests: List<Quest>,
        weather: String,
        congestion: String
    ): List<Quest> {
        val questsJson = quests.mapIndexed { i, q ->
            """{"id":$i,"index":${q.index},"name":"${q.questName}","difficulty":${q.difficulty}}"""
        }.joinToString(",", "[", "]")

        val prompt = """
            아래 환경 데이터와 퀘스트 목록을 보고, 오늘 활동에 가장 적합한 퀘스트 8개를 골라줘.

            오늘 날씨: $weather
            혼잡도: $congestion

            퀘스트 목록(JSON):
            $questsJson

            응답 형식: 선택한 퀘스트의 id 숫자만 JSON 배열로 반환. 예: [0,2,5,8,11,14,17,19]
            다른 텍스트, 마크다운 없이 JSON 배열만.
        """.trimIndent()

        val response = GeminiClient.service.generateContent(
            apiKey  = BuildConfig.GEMINI_API_KEY,
            request = GeminiRequest(listOf(GeminiContent(listOf(GeminiPart(prompt)))))
        )

        val rawText = response.candidates
            ?.firstOrNull()?.content?.parts?.firstOrNull()?.text
            ?: return quests.shuffled().take(8)

        return try {
            val clean = rawText.trim()
                .removePrefix("```json").removePrefix("```").removeSuffix("```").trim()
            val arr = JSONArray(clean)
            (0 until arr.length())
                .map { arr.getInt(it) }
                .mapNotNull { quests.getOrNull(it) }
                .take(8)
                .ifEmpty { quests.shuffled().take(8) }
        } catch (e: Exception) {
            quests.shuffled().take(8)
        }
    }
}
