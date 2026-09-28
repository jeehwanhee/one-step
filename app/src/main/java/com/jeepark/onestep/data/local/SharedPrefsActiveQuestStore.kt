package com.jeepark.onestep.data.local

import android.content.Context
import com.jeepark.onestep.data.repository.ActiveQuestStore
import com.jeepark.onestep.domain.model.Quest

/**
 * SharedPreferences 기반 구현.
 * 파일 이름([FILE_NAME])과 키는 이전 MainViewModel 구현과 동일하게 유지해야 한다 —
 * 바뀌면 앱 업데이트 후 저장돼 있던 진행 중 퀘스트가 사라진다.
 */
class SharedPrefsActiveQuestStore(context: Context) : ActiveQuestStore {
    private val prefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    override fun save(quest: Quest) {
        prefs.edit()
            .putInt(KEY_INDEX, quest.index)
            .putString(KEY_QUEST_NAME, quest.questName)
            .putInt(KEY_DIFFICULTY, quest.difficulty)
            .putString(KEY_CONFIRM_QUESTION, quest.confirmQuestion)
            .putInt(KEY_QUEST_EXP, quest.questEXP)
            .apply()
    }

    override fun clear() {
        prefs.edit().clear().apply()
    }

    override fun load(): Quest? {
        val name = prefs.getString(KEY_QUEST_NAME, null) ?: return null
        return Quest(
            index           = prefs.getInt(KEY_INDEX, 0),
            questName       = name,
            difficulty      = prefs.getInt(KEY_DIFFICULTY, 1),
            confirmQuestion = prefs.getString(KEY_CONFIRM_QUESTION, "") ?: "",
            questEXP        = prefs.getInt(KEY_QUEST_EXP, 0)
        )
    }

    companion object {
        const val FILE_NAME = "active_quest"
        const val KEY_INDEX = "index"
        const val KEY_QUEST_NAME = "questName"
        const val KEY_DIFFICULTY = "difficulty"
        const val KEY_CONFIRM_QUESTION = "confirmQuestion"
        const val KEY_QUEST_EXP = "questEXP"
    }
}
