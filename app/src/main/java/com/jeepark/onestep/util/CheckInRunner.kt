package com.jeepark.onestep.util

import com.jeepark.onestep.data.repository.SettingsRepository
import java.time.Clock
import java.util.concurrent.TimeUnit

/**
 * 안부 알림 워커가 한 번 실행될 때의 판단과 기록. WorkManager·알림 표시(Android)는 워커에 남기고,
 * "보낼지, 어떤 메시지인지, 이후 예약을 끝낼지"는 여기서 정해서 단위 테스트할 수 있게 했다.
 *
 * @param send 알림을 실제로 표시하는 함수
 */
class CheckInRunner(
    private val settings: SettingsRepository,
    private val scheduler: NotificationScheduler,
    private val clock: Clock = Clock.systemDefaultZone(),
    private val send: (CheckInPolicy.Message) -> Unit,
) {

    fun run() {
        if (!settings.notificationsEnabled) return

        val lastAccess = settings.lastAccessMillis
        if (lastAccess == 0L) {
            // 메인 화면에 도달한 적 없는 사용자에게는 안부를 보내지 않는다
            scheduler.cancel()
            return
        }

        val daysSince = TimeUnit.MILLISECONDS.toDays(clock.millis() - lastAccess).toInt()
        val mark = CheckInPolicy.latestDueMark(daysSince, settings.lastCheckInMark) ?: return
        val message = CheckInPolicy.MESSAGES[mark] ?: return

        send(message)
        settings.recordCheckIn(mark)

        if (mark == CheckInPolicy.FINAL_MARK) scheduler.cancel()
    }
}
