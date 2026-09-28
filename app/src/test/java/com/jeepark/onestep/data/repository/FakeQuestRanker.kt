package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.data.model.SELECTION_SIZE

/**
 * 테스트용 가짜 QuestRanker. 기본으로는 받은 후보의 앞에서부터 [SELECTION_SIZE]개를 돌려주고,
 * [error]를 설정하면 그 예외를 던진다. 받은 인자를 기록한다.
 */
class FakeQuestRanker : QuestRanker {

    var error: Throwable? = null
    var result: ((List<Quest>) -> List<Quest>)? = null

    var receivedQuests: List<Quest>? = null
        private set
    var receivedWeather: String? = null
        private set
    var callCount = 0
        private set

    override suspend fun select(quests: List<Quest>, weather: String): List<Quest> {
        callCount++
        receivedQuests = quests
        receivedWeather = weather
        error?.let { throw it }
        return result?.invoke(quests) ?: quests.take(SELECTION_SIZE)
    }
}
