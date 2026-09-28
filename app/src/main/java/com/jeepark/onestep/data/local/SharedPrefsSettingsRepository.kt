package com.jeepark.onestep.data.local

import android.content.Context
import android.content.SharedPreferences
import com.jeepark.onestep.data.repository.SettingsRepository

/**
 * SharedPreferences 구현. 파일 이름과 키는 이미 설치된 기기에 저장된 값을 읽어야 하므로 바꾸면 안 된다.
 */
class SharedPrefsSettingsRepository(private val prefs: SharedPreferences) : SettingsRepository {

    override val notificationsEnabled: Boolean
        get() = prefs.getBoolean(KEY_NOTIFICATIONS_ENABLED, true)

    override val lastAccessMillis: Long
        get() = prefs.getLong(KEY_LAST_ACCESS, 0L)

    override val lastCheckInMark: Int
        get() = prefs.getInt(KEY_LAST_CHECKIN_MARK, 0)

    override val permissionsRequested: Boolean
        get() = prefs.getBoolean(KEY_PERMISSIONS_REQUESTED, false)

    override fun setNotificationsEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_NOTIFICATIONS_ENABLED, enabled).apply()
    }

    override fun recordAccess(atMillis: Long) {
        prefs.edit()
            .putLong(KEY_LAST_ACCESS, atMillis)
            .putInt(KEY_LAST_CHECKIN_MARK, 0)
            .apply()
    }

    override fun recordCheckIn(mark: Int) {
        prefs.edit().putInt(KEY_LAST_CHECKIN_MARK, mark).apply()
    }

    override fun markPermissionsRequested() {
        prefs.edit().putBoolean(KEY_PERMISSIONS_REQUESTED, true).apply()
    }

    override fun clearAccessRecord() {
        prefs.edit()
            .remove(KEY_LAST_ACCESS)
            .remove(KEY_LAST_CHECKIN_MARK)
            .apply()
    }

    companion object {
        const val FILE_NAME = "app_settings"
        const val KEY_NOTIFICATIONS_ENABLED = "notification_enabled"
        const val KEY_LAST_ACCESS = "last_access_date"
        const val KEY_LAST_CHECKIN_MARK = "last_checkin_mark"
        const val KEY_PERMISSIONS_REQUESTED = "perm_requested"

        fun create(context: Context): SharedPrefsSettingsRepository =
            SharedPrefsSettingsRepository(
                context.applicationContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            )
    }
}
