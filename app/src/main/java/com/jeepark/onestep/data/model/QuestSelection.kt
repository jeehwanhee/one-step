package com.jeepark.onestep.data.model

import kotlin.math.roundToInt
import kotlin.random.Random

// 전체 퀘스트에서 사용자에게 보여줄 후보를 고르는 규칙 (순수 함수).
// 난수는 주입받아서 테스트에서 결과를 고정할 수 있다.

/** 난이도 비율에 맞춰 먼저 뽑아 두는 후보 수. */
const val SAMPLE_SIZE = 20

/** 사용자에게 최종으로 보여주는 퀘스트 수. */
const val SELECTION_SIZE = 8

/** 퀘스트 난이도 단계(1..5). [sampleByRatio]에서 비율 목록의 i번째 값이 난이도 i+1의 비율이다. */
val DIFFICULTY_LEVELS = 1..5

/**
 * 난이도별 [ratios]에 맞춰 [all]에서 [total]개를 무작위로 뽑는다.
 * 난이도마다 (비율 × total)을 반올림한 개수만큼 뽑고, 그 난이도의 퀘스트가 모자라거나 반올림 때문에
 * [total]에 못 미치면 남은 퀘스트로 채운다. 결과는 최대 [total]개다.
 */
fun sampleByRatio(all: List<Quest>, ratios: List<Double>, total: Int, random: Random): List<Quest> {
    val byLevel = DIFFICULTY_LEVELS.associateWith { level ->
        all.filter { it.difficulty == level }.shuffled(random)
    }
    val result = mutableListOf<Quest>()

    for (level in DIFFICULTY_LEVELS) {
        val count = (ratios.getOrElse(level - 1) { 0.0 } * total).roundToInt()
        result.addAll((byLevel[level] ?: emptyList()).take(count))
    }

    // 부족하면 남은 퀘스트로 채움
    if (result.size < total) {
        val extras = all.filterNot { it in result }.shuffled(random)
        result.addAll(extras.take(total - result.size))
    }

    return result.take(total)
}

/** 추천 서비스를 쓸 수 없을 때(한도 초과, 실패)의 대체 선택: 후보에서 무작위로 [SELECTION_SIZE]개. */
fun randomSelection(quests: List<Quest>, random: Random): List<Quest> =
    quests.shuffled(random).take(SELECTION_SIZE)
