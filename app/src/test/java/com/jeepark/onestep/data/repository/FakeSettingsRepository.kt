package com.jeepark.onestep.data.repository

/**
 * 테스트용 메모리 기반 SettingsRepository. 실제 구현과 같은 기본값·동작을 따른다.
 * 값은 생성자로 미리 정하고, 이후에는 인터페이스의 메서드로만 바꾼다(실제 구현과 같은 경로).
 */
class FakeSettingsRepository(
    notificationsEnabled: Boolean = true,
    lastAccessMillis: Long = 0L,
    lastCheckInMark: Int = 0,
    permissionsRequested: Boolean = false,
) : SettingsRepository {

    override var notificationsEnabled: Boolean = notificationsEnabled
        private set

    override var lastAccessMillis: Long = lastAccessMillis
        private set

    override var lastCheckInMark: Int = lastCheckInMark
        private set

    override var permissionsRequested: Boolean = permissionsRequested
        private set

    override fun setNotificationsEnabled(enabled: Boolean) {
        notificationsEnabled = enabled
    }

    override fun recordAccess(atMillis: Long) {
        lastAccessMillis = atMillis
        lastCheckInMark = 0
    }

    override fun recordCheckIn(mark: Int) {
        lastCheckInMark = mark
    }

    override fun markPermissionsRequested() {
        permissionsRequested = true
    }

    override fun clearAccessRecord() {
        lastAccessMillis = 0L
        lastCheckInMark = 0
    }
}
