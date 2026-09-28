package com.jeepark.onestep.data.repository

import org.junit.Assert.assertEquals
import org.junit.Test

class SharedPrefsVersionStoreTest {

    private val prefs = InMemorySharedPreferences()

    @Test
    fun `파일 이름과 키는 기존 설치 기기에 저장된 값과 같다`() {
        assertEquals("versioned_cache", SharedPrefsVersionStore.FILE_NAME)
        assertEquals("quests_version", SharedPrefsVersionStore.keyFor("quests"))
        assertEquals("places_version", SharedPrefsVersionStore.keyFor("places"))
    }

    @Test
    fun `저장된 버전이 없으면 없음을 돌려준다`() {
        assertEquals(NO_VERSION, SharedPrefsVersionStore(prefs, "quests_version").read())
    }

    @Test
    fun `옛 앱이 저장해 둔 버전을 그대로 읽는다`() {
        prefs.edit().putLong("quests_version", 12L).apply()

        assertEquals(12L, SharedPrefsVersionStore(prefs, SharedPrefsVersionStore.keyFor("quests")).read())
    }

    @Test
    fun `버전을 쓰면 다시 읽을 수 있다`() {
        val store = SharedPrefsVersionStore(prefs, "places_version")

        store.write(3L)

        assertEquals(3L, store.read())
        assertEquals(3L, prefs.getLong("places_version", NO_VERSION))
    }

    @Test
    fun `컬렉션마다 버전이 따로 저장된다`() {
        val quests = SharedPrefsVersionStore(prefs, SharedPrefsVersionStore.keyFor("quests"))
        val places = SharedPrefsVersionStore(prefs, SharedPrefsVersionStore.keyFor("places"))

        quests.write(7L)

        assertEquals(7L, quests.read())
        assertEquals(NO_VERSION, places.read())
    }
}
