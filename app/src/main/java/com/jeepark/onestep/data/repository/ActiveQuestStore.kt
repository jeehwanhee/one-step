package com.jeepark.onestep.data.repository

import com.jeepark.onestep.domain.model.Quest

/** 진행 중인 퀘스트를 앱 재시작 후에도 복원할 수 있도록 보관하는 저장소. */
interface ActiveQuestStore {
    fun save(quest: Quest)
    fun clear()
    fun load(): Quest?
}
