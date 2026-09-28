package com.jeepark.onestep.domain.service

import com.jeepark.onestep.domain.model.Quest
import org.json.JSONArray
import org.json.JSONObject
import kotlin.random.Random

// Gemini에게 오늘 할 만한 퀘스트를 골라 달라고 요청하는 프롬프트와, 응답 해석 (네트워크 없이 테스트 가능).

/**
 * 후보 퀘스트와 날씨로 Gemini에 보낼 프롬프트를 만든다.
 * 퀘스트는 JSONObject로 직렬화해서 이름에 따옴표·역슬래시·줄바꿈이 있어도 깨지지 않는다.
 * 각 퀘스트의 "id"는 [quests] 안에서의 위치이고, 응답은 이 id 숫자의 배열이어야 한다.
 */
fun buildRankingPrompt(quests: List<Quest>, weather: String): String {
    val questsJson = JSONArray().also { array ->
        quests.forEachIndexed { i, q ->
            array.put(JSONObject().apply {
                put("id", i)
                put("index", q.index)
                put("name", q.questName)
                put("difficulty", q.difficulty)
            })
        }
    }.toString()

    return """
        아래 환경 데이터와 퀘스트 목록을 보고, 오늘 활동에 가장 적합한 퀘스트 ${SELECTION_SIZE}개를 골라줘.

        오늘 날씨: $weather

        퀘스트 목록(JSON):
        $questsJson

        응답 형식: 선택한 퀘스트의 id 숫자만 JSON 배열로 반환. 예: [0,2,5,8,11,14,17,19]
        다른 텍스트, 마크다운 없이 JSON 배열만.
    """.trimIndent()
}

/**
 * Gemini의 응답 텍스트에서 고른 id를 읽어 [quests]의 해당 퀘스트를 최대 [SELECTION_SIZE]개 돌려준다.
 * 마크다운 코드 블록(```json)이 붙어 있어도 읽는다. 범위 밖 id는 무시하고,
 * 읽을 수 없거나 유효한 id가 하나도 없으면 [randomSelection]으로 대신한다.
 */
fun parseRankedSelection(rawText: String, quests: List<Quest>, random: Random): List<Quest> = try {
    val clean = rawText.trim()
        .removePrefix("```json").removePrefix("```").removeSuffix("```").trim()
    val ids = JSONArray(clean)
    (0 until ids.length())
        .map { ids.getInt(it) }
        .mapNotNull { quests.getOrNull(it) }
        .take(SELECTION_SIZE)
        .ifEmpty { randomSelection(quests, random) }
} catch (e: Exception) {
    randomSelection(quests, random)
}
