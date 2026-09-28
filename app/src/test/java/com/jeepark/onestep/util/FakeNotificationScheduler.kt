package com.jeepark.onestep.util

/** 테스트용 가짜 NotificationScheduler. 호출 횟수와 현재 예약 여부를 기록한다. */
class FakeNotificationScheduler : NotificationScheduler {
    var channelCreateCount = 0
        private set
    var scheduleCount = 0
        private set
    var cancelCount = 0
        private set

    /** 마지막 호출 기준으로 예약돼 있는지. */
    var isScheduled = false
        private set

    override fun createChannel() {
        channelCreateCount++
    }

    override fun schedule() {
        scheduleCount++
        isScheduled = true
    }

    override fun cancel() {
        cancelCount++
        isScheduled = false
    }
}
