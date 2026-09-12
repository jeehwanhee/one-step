package com.jeepark.onestep.util

import android.content.Context
import com.jeepark.onestep.BuildConfig
import com.jeepark.onestep.data.model.GeminiClient
import com.jeepark.onestep.data.model.GeminiContent
import com.jeepark.onestep.data.model.GeminiPart
import com.jeepark.onestep.data.model.GeminiRequest
import com.jeepark.onestep.data.model.NetworkClient
import com.jeepark.onestep.data.model.Quest
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.roundToInt

class QuestRepository(context: Context) {
    private val appContext = context.applicationContext

    private val questsCache = VersionedCache<Quest>(
        context = appContext,
        collection = "quests",
        metaDocId = "quests_meta",
    ) { doc -> try { doc.toObject(Quest::class.java) } catch (e: Exception) { null } }

    suspend fun fetchFilteredQuests(
        ratios: List<Double>,
        useGemini: Boolean = true
    ): List<Quest> {
        // 1. quests 컬렉션 로드 (버전 키 캐싱)
        val allQuests = questsCache.load().filter { it.questName.isNotEmpty() }
        if (allQuests.isEmpty()) throw Exception("quests 컬렉션이 비어 있습니다")

        // 2. 비율에 맞게 20개 샘플링
        val sampled = sampleByRatio(allQuests, ratios, 20)

        // 3. {공원}/{도서관} 등 플레이스홀더를 사용자 위치 기반 실제 장소명으로 치환
        val substituted = substitutePlaceholders(sampled)

        // 4. 일일 한도 초과 시 Gemini 건너뛰고 무작위 8개 반환
        if (!useGemini) {
            return substituted.shuffled().take(8)
        }

        // 5. 날씨 조회 (기상청 — 전국 커버)
        val weather = fetchWeather()

        // 6. Gemini로 8개 선별 (실패 시 랜덤 8개 반환)
        return try {
            selectWithGemini(substituted, weather)
        } catch (e: Exception) {
            // HttpException이면 응답 본문도 출력 (어떤 quota인지 확인용)
            if (e is retrofit2.HttpException) {
                val body = try { e.response()?.errorBody()?.string() } catch (_: Exception) { null }
                android.util.Log.e("QuestRepo", "Gemini ${e.code()} body=$body")
            } else {
                android.util.Log.e("QuestRepo", "Gemini 호출 실패", e)
            }
            substituted.shuffled().take(8)
        }
    }

    private val PLACEHOLDERS = mapOf(
        "{공원}"          to "park",
        "{도서관}"        to "library",
        "{청년공간}"      to "youth_space",
        "{정신건강센터}"  to "mental_center",
        "{체육시설}"      to "gym",
    )

    private val FALLBACK_NAMES = mapOf(
        "{공원}"          to "근처 공원",
        "{도서관}"        to "근처 도서관",
        "{청년공간}"      to "근처 청년공간",
        "{정신건강센터}"  to "가까운 정신건강복지센터",
        "{체육시설}"      to "근처 체육시설",
    )

    private suspend fun substitutePlaceholders(quests: List<Quest>): List<Quest> {
        // 어떤 quest에라도 플레이스홀더가 있는 경우만 places 로드
        val hasAny = quests.any { q -> PLACEHOLDERS.keys.any { it in q.questName || it in q.confirmQuestion } }
        if (!hasAny) return quests

        val allPlaces = try { PlaceRepository.loadAll(appContext) } catch (e: Exception) { return quests }

        return quests.map { quest ->
            var name = quest.questName
            var question = quest.confirmQuestion
            for ((placeholder, type) in PLACEHOLDERS) {
                if (placeholder !in name && placeholder !in question) continue
                val place = PlaceRepository.findNearestPlace(type, allPlaces)
                val replacement = place?.name ?: (FALLBACK_NAMES[placeholder] ?: placeholder)
                name = name.replace(placeholder, replacement)
                question = question.replace(placeholder, replacement)
            }
            quest.copy(questName = name, confirmQuestion = question)
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

    private suspend fun fetchWeather(): String = try {
        val lat = LocationHelper.currentLat
        val lng = LocationHelper.currentLng
        if (lat == null || lng == null) {
            "정보 없음"
        } else {
            val (nx, ny) = LocationHelper.latLngToGrid(lat, lng)

            // 초단기실황 base_time: 매시 정시 발표(약 40분 지연) → 안전하게 1시간 전 정시 사용
            val cal = java.util.Calendar.getInstance().apply {
                add(java.util.Calendar.HOUR_OF_DAY, -1)
            }
            val baseDate = java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA).format(cal.time)
            val baseTime = java.text.SimpleDateFormat("HH00", java.util.Locale.KOREA).format(cal.time)

            val resp = NetworkClient.kmaService.getUltraSrtNcst(
                serviceKey = BuildConfig.KMA_API_KEY,
                baseDate = baseDate, baseTime = baseTime, nx = nx, ny = ny
            )
            val items = resp.response?.body?.items?.item ?: emptyList()
            val temp = items.firstOrNull { it.category == "T1H" }?.obsrValue
            val pty  = items.firstOrNull { it.category == "PTY" }?.obsrValue
            val ptyMsg = when (pty) {
                "0" -> "강수 없음"
                "1" -> "비"
                "2" -> "비/눈"
                "3" -> "눈"
                "5" -> "빗방울"
                "6" -> "빗방울/눈날림"
                "7" -> "눈날림"
                else -> "강수 정보 없음"
            }
            if (temp != null) "기온 ${temp}°C, $ptyMsg" else "정보 없음"
        }
    } catch (e: Exception) {
        android.util.Log.e("QuestRepo", "기상청 API 호출 실패", e)
        "정보 없음"
    }

    private suspend fun selectWithGemini(
        quests: List<Quest>,
        weather: String,
    ): List<Quest> {
        // JSONObject로 안전하게 직렬화 (퀘스트명에 ", \, 줄바꿈 등 있어도 깨지지 않음)
        val questsJsonArr = JSONArray()
        quests.forEachIndexed { i, q ->
            questsJsonArr.put(JSONObject().apply {
                put("id", i)
                put("index", q.index)
                put("name", q.questName)
                put("difficulty", q.difficulty)
            })
        }
        val questsJson = questsJsonArr.toString()

        val prompt = """
            아래 환경 데이터와 퀘스트 목록을 보고, 오늘 활동에 가장 적합한 퀘스트 8개를 골라줘.

            오늘 날씨: $weather

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
