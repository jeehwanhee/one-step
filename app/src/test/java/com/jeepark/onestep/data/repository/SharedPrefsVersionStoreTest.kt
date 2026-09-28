package com.jeepark.onestep.data.repository

import org.junit.Assert.assertEquals
import org.junit.Test

class SharedPrefsVersionStoreTest {

    @Test
    fun `파일 이름과 키는 기존 설치 기기에 저장된 값과 같다`() {
        assertEquals("versioned_cache", SharedPrefsVersionStore.FILE_NAME)
        assertEquals("quests_version", SharedPrefsVersionStore.keyFor("quests"))
        assertEquals("places_version", SharedPrefsVersionStore.keyFor("places"))
    }
}
