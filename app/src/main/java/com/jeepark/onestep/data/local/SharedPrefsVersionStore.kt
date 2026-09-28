package com.jeepark.onestep.data.local

import android.content.SharedPreferences
import com.jeepark.onestep.data.repository.NO_VERSION
import com.jeepark.onestep.data.repository.VersionStore

/**
 * SharedPreferences 구현. 파일 이름과 키는 이미 설치된 기기에 저장된 버전을 읽어야 하므로 바꾸면 안 된다.
 * 버전을 잃으면 다음 실행 때 컬렉션을 한 번 더 내려받게 될 뿐이지만, 이름이 바뀌면 그런 일이 모든 사용자에게 생긴다.
 */
class SharedPrefsVersionStore(
    private val prefs: SharedPreferences,
    private val key: String,
) : VersionStore {

    override fun read(): Long = prefs.getLong(key, NO_VERSION)

    override fun write(version: Long) {
        prefs.edit().putLong(key, version).apply()
    }

    companion object {
        const val FILE_NAME = "versioned_cache"

        /** 컬렉션 이름으로 만드는 버전 키. 예: "quests" → "quests_version". */
        fun keyFor(collection: String): String = "${collection}_version"
    }
}
