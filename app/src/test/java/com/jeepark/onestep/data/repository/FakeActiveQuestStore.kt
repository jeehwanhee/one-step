package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.Quest

/** 테스트용 메모리 기반 ActiveQuestStore. */
class FakeActiveQuestStore(var stored: Quest? = null) : ActiveQuestStore {
    var saveCount = 0
    var clearCount = 0

    override fun save(quest: Quest) {
        stored = quest
        saveCount++
    }

    override fun clear() {
        stored = null
        clearCount++
    }

    override fun load(): Quest? = stored
}
