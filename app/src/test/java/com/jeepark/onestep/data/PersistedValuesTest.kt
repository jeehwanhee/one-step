package com.jeepark.onestep.data

import com.jeepark.onestep.data.model.Gender
import com.jeepark.onestep.data.model.GiveUpReason
import com.jeepark.onestep.data.model.Mood
import com.jeepark.onestep.data.repository.SharedPrefsSettingsRepository
import com.jeepark.onestep.data.repository.SharedPrefsVersionStore
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 이미 사용자 기기와 서버에 저장된 값을 읽기 위해 바뀌면 안 되는 이름·코드를 한 곳에서 고정한다.
 * 이 중 하나가 바뀌면 기존 사용자의 데이터가 오류 없이 조용히 사라지거나 다르게 읽힌다.
 */
class PersistedValuesTest {

    @Test
    fun `이미 저장된 값의 이름과 코드는 바뀌지 않았다`() {
        // Firestore에 저장되는 값
        assertEquals(true, Gender.MALE.storedValue)
        assertEquals(false, Gender.FEMALE.storedValue)
        assertEquals(listOf(1, 2, 3), GiveUpReason.entries.map { it.code })

        // 난이도 예측 모델의 입력값
        assertEquals(listOf(1, 2, 3, 4, 5), Mood.entries.map { it.level })

        // 기기 SharedPreferences의 파일 이름과 키
        assertEquals(
            listOf("app_settings", "notification_enabled", "last_access_date", "last_checkin_mark", "perm_requested"),
            listOf(
                SharedPrefsSettingsRepository.FILE_NAME,
                SharedPrefsSettingsRepository.KEY_NOTIFICATIONS_ENABLED,
                SharedPrefsSettingsRepository.KEY_LAST_ACCESS,
                SharedPrefsSettingsRepository.KEY_LAST_CHECKIN_MARK,
                SharedPrefsSettingsRepository.KEY_PERMISSIONS_REQUESTED,
            ),
        )
        assertEquals(
            listOf("versioned_cache", "quests_version", "places_version"),
            listOf(
                SharedPrefsVersionStore.FILE_NAME,
                SharedPrefsVersionStore.keyFor("quests"),
                SharedPrefsVersionStore.keyFor("places"),
            ),
        )
    }
}
