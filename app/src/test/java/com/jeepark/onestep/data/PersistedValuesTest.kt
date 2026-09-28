package com.jeepark.onestep.data

import com.jeepark.onestep.data.local.SharedPrefsActiveQuestStore
import com.jeepark.onestep.data.local.SharedPrefsSettingsRepository
import com.jeepark.onestep.data.local.SharedPrefsVersionStore
import com.jeepark.onestep.domain.model.FirestorePaths
import com.jeepark.onestep.domain.model.Gender
import com.jeepark.onestep.domain.model.GiveUpEntryFields
import com.jeepark.onestep.domain.model.GiveUpReason
import com.jeepark.onestep.domain.model.IsolatedRecord
import com.jeepark.onestep.domain.model.IsolatedRecordFields
import com.jeepark.onestep.domain.model.Mood
import com.jeepark.onestep.domain.model.PrevQuest
import com.jeepark.onestep.domain.model.PrevQuestFields
import com.jeepark.onestep.domain.model.Quest
import com.jeepark.onestep.domain.model.QuestFields
import com.jeepark.onestep.domain.model.User
import com.jeepark.onestep.domain.model.UserFields
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.reflect.Modifier

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

        // Firestore 컬렉션·필드 이름
        assertEquals(
            listOf("users", "quests", "places", "meta", "quests_meta", "places_meta", "version").sorted(),
            constantsOf(FirestorePaths::class.java),
        )
        assertEquals(
            listOf(
                "initQuestions", "isolated", "isolatedLastModified", "isolatedHistory", "questsSinceAssessment",
                "progress", "tier", "difficultyQueue", "questResultsQueue", "prevQuests", "lastAccessDate",
                "notificationAgreed", "dailyQuestCount", "dailyQuestDate", "giveUpReasons",
            ).sorted(),
            constantsOf(UserFields::class.java),
        )
        assertEquals(listOf("index", "giveUpReasons").sorted(), constantsOf(QuestFields::class.java))
        assertEquals(listOf("score", "recordedAt").sorted(), constantsOf(IsolatedRecordFields::class.java))
        assertEquals(
            listOf("questName", "questEXP", "difficulty", "confirmQuestion", "confirmAnswer", "doneDate").sorted(),
            constantsOf(PrevQuestFields::class.java),
        )
        assertEquals(listOf("questName", "reason").sorted(), constantsOf(GiveUpEntryFields::class.java))

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
        assertEquals(
            listOf("active_quest", "index", "questName", "difficulty", "confirmQuestion", "questEXP"),
            listOf(
                SharedPrefsActiveQuestStore.FILE_NAME,
                SharedPrefsActiveQuestStore.KEY_INDEX,
                SharedPrefsActiveQuestStore.KEY_QUEST_NAME,
                SharedPrefsActiveQuestStore.KEY_DIFFICULTY,
                SharedPrefsActiveQuestStore.KEY_CONFIRM_QUESTION,
                SharedPrefsActiveQuestStore.KEY_QUEST_EXP,
            ),
        )
    }

    /**
     * 필드 이름 상수가 모델 프로퍼티와 어긋나면 `update(...)`로 쓴 값을 `toObject`가 읽지 못한다(오류 없이 기본값이 된다).
     * `giveUpReasons`는 쓰기 전용 통계 필드라 모델에 두지 않는다(FirestoreSchema.kt).
     */
    @Test
    fun `Firestore 필드 이름 상수는 모델 프로퍼티와 일치한다`() {
        val writeOnly = setOf(UserFields.GIVE_UP_REASONS, QuestFields.GIVE_UP_REASONS)

        assertEquals(emptySet<String>(), constantsOf(UserFields::class.java).toSet() - writeOnly - propertiesOf(User::class.java))
        assertEquals(emptySet<String>(), constantsOf(QuestFields::class.java).toSet() - writeOnly - propertiesOf(Quest::class.java))
        assertEquals(propertiesOf(IsolatedRecord::class.java), constantsOf(IsolatedRecordFields::class.java).toSet())
        assertEquals(propertiesOf(PrevQuest::class.java), constantsOf(PrevQuestFields::class.java).toSet())
    }

    /** `const val` 문자열 상수를 정렬해서 읽는다(리플렉션은 필드 순서를 보장하지 않는다). */
    private fun constantsOf(holder: Class<*>): List<String> =
        holder.declaredFields
            .filter { Modifier.isStatic(it.modifiers) && it.type == String::class.java }
            .map { it.apply { isAccessible = true }.get(null) as String }
            .sorted()

    /** 모델(data class)의 프로퍼티 이름. */
    private fun propertiesOf(model: Class<*>): Set<String> =
        model.declaredFields
            .filter { !Modifier.isStatic(it.modifiers) && !it.isSynthetic }
            .map { it.name }
            .toSet()
}
