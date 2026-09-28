package com.jeepark.onestep.util

import android.content.Context

/**
 * 안부 알림의 채널 생성과 예약. 화면과 서비스는 WorkManager를 직접 만지지 않고 이 인터페이스만 쓴다.
 * 테스트에서는 FakeNotificationScheduler로 교체할 수 있다.
 */
interface NotificationScheduler {
    /** 알림 채널을 만든다(이미 있으면 그대로 둔다). */
    fun createChannel()

    /** 매일 저녁 8시에 안부 알림을 확인하도록 예약한다(이미 예약돼 있으면 다시 잡는다). */
    fun schedule()

    /** 예약된 안부 알림을 취소한다. */
    fun cancel()
}

/** [NotificationHelper]에 위임하는 WorkManager 기반 구현. */
class WorkManagerNotificationScheduler(context: Context) : NotificationScheduler {

    private val appContext = context.applicationContext

    override fun createChannel() = NotificationHelper.createChannel(appContext)

    override fun schedule() = NotificationHelper.schedule(appContext)

    override fun cancel() = NotificationHelper.cancel(appContext)
}
