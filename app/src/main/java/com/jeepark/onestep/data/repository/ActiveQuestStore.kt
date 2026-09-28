package com.jeepark.onestep.data.repository

import android.content.Context
import com.jeepark.onestep.data.model.Quest

/** 진행 중인 퀘스트를 앱 재시작 후에도 복원할 수 있도록 보관하는 저장소. */
interface ActiveQuestStore {
    fun save(quest: Quest)
    fun clear()
    fun load(): Quest?
}

/**
 * SharedPreferences 기반 구현.
 * prefs 이름("active_quest")과 키는 이전 MainViewModel 구현과 동일하게 유지해야 한다 —
 * 바뀌면 앱 업데이트 후 저장돼 있던 진행 중 퀘스트가 사라진다.
 */
class SharedPrefsActiveQuestStore(context: Context) : ActiveQuestStore {
    private val prefs = context.getSharedPreferences("active_quest", Context.MODE_PRIVATE)

    override fun save(quest: Quest) {
        prefs.edit()
            .putInt("index", quest.index)
            .putString("questName", quest.questName)
            .putInt("difficulty", quest.difficulty)
            .putString("confirmQuestion", quest.confirmQuestion)
            .putInt("questEXP", quest.questEXP)
            .apply()
    }

    override fun clear() {
        prefs.edit().clear().apply()
    }

    override fun load(): Quest? {
        val name = prefs.getString("questName", null) ?: return null
        return Quest(
            index           = prefs.getInt("index", 0),
            questName       = name,
            difficulty      = prefs.getInt("difficulty", 1),
            confirmQuestion = prefs.getString("confirmQuestion", "") ?: "",
            questEXP        = prefs.getInt("questEXP", 0)
        )
    }
}
