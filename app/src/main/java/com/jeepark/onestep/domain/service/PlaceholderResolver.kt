package com.jeepark.onestep.domain.service

import com.jeepark.onestep.domain.model.Place
import com.jeepark.onestep.domain.model.Quest

// 퀘스트 문구의 {공원}/{도서관} 같은 자리표시자를 실제 장소 이름으로 바꾼다 (순수 함수).

/** 자리표시자 → 장소 종류(places 컬렉션의 type). */
private val PLACEHOLDER_TYPES = mapOf(
    "{공원}"          to "park",
    "{도서관}"        to "library",
    "{청년공간}"      to "youth_space",
    "{정신건강센터}"  to "mental_center",
    "{체육시설}"      to "gym",
)

/** 가까운 장소를 찾지 못했을 때 대신 쓰는 이름. */
private val FALLBACK_NAMES = mapOf(
    "{공원}"          to "근처 공원",
    "{도서관}"        to "근처 도서관",
    "{청년공간}"      to "근처 청년공간",
    "{정신건강센터}"  to "가까운 정신건강복지센터",
    "{체육시설}"      to "근처 체육시설",
)

/** 어떤 퀘스트라도 자리표시자를 쓰고 있는지. 없으면 장소 데이터를 불러올 필요가 없다. */
fun hasPlaceholders(quests: List<Quest>): Boolean =
    quests.any { q -> PLACEHOLDER_TYPES.keys.any { it in q.questName || it in q.confirmQuestion } }

/**
 * 각 퀘스트의 이름과 확인 질문에 든 자리표시자를 장소 이름으로 바꾼다.
 * 장소는 퀘스트마다, 자리표시자마다 [findNearestPlace]로 따로 고르므로 같은 종류라도 퀘스트마다 다른 장소가 나올 수 있다.
 * 장소를 찾지 못하면 "근처 공원" 같은 대체 이름을 쓴다. 한 퀘스트 안의 같은 자리표시자는 모두 같은 이름으로 바뀐다.
 */
fun resolvePlaceholders(quests: List<Quest>, findNearestPlace: (type: String) -> Place?): List<Quest> =
    quests.map { quest ->
        var name = quest.questName
        var question = quest.confirmQuestion
        for ((placeholder, type) in PLACEHOLDER_TYPES) {
            if (placeholder !in name && placeholder !in question) continue
            val place = findNearestPlace(type)
            val replacement = place?.name ?: (FALLBACK_NAMES[placeholder] ?: placeholder)
            name = name.replace(placeholder, replacement)
            question = question.replace(placeholder, replacement)
        }
        quest.copy(questName = name, confirmQuestion = question)
    }
