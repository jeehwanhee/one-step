package com.jeepark.onestep.util

import com.jeepark.onestep.data.repository.FakeSettingsRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.time.ZoneOffset

class CheckInRunnerTest {

    private val now: Instant = Instant.parse("2026-09-28T12:00:00Z")
    private val clock: Clock = Clock.fixed(now, ZoneOffset.UTC)

    private class Fixture(val settings: FakeSettingsRepository, clock: Clock) {
        val scheduler = FakeNotificationScheduler().apply { schedule() }
        val sent = mutableListOf<CheckInPolicy.Message>()
        val runner = CheckInRunner(settings, scheduler, clock) { sent.add(it) }
    }

    /** 마지막 접속이 [days]일 전인 상태. */
    private fun fixture(
        days: Long,
        lastMark: Int = 0,
        enabled: Boolean = true,
    ): Fixture {
        val lastAccess = now.minus(Duration.ofDays(days)).toEpochMilli()
        return Fixture(FakeSettingsRepository(notificationsEnabled = enabled, lastAccessMillis = lastAccess, lastCheckInMark = lastMark), clock)
    }

    @Test
    fun `알림을 끄면 아무것도 보내지 않고 예약도 그대로 둔다`() {
        val f = fixture(days = 10, enabled = false)

        f.runner.run()

        assertTrue(f.sent.isEmpty())
        assertTrue(f.scheduler.isScheduled)
        assertEquals(0, f.settings.lastCheckInMark)
    }

    @Test
    fun `접속 기록이 없으면 안부를 보내지 않고 예약을 취소한다`() {
        val f = Fixture(FakeSettingsRepository(lastAccessMillis = 0L), clock)

        f.runner.run()

        assertTrue(f.sent.isEmpty())
        assertEquals(1, f.scheduler.cancelCount)
    }

    @Test
    fun `2일이 지나면 첫 안부를 보내고 단계를 기록한다`() {
        val f = fixture(days = 2)

        f.runner.run()

        assertEquals(listOf(CheckInPolicy.MESSAGES.getValue(2)), f.sent)
        assertEquals(2, f.settings.lastCheckInMark)
        assertTrue(f.scheduler.isScheduled)
    }

    @Test
    fun `마지막 단계를 보내면 더는 보내지 않도록 예약을 취소한다`() {
        val f = fixture(days = 45)

        f.runner.run()

        assertEquals(listOf(CheckInPolicy.MESSAGES.getValue(CheckInPolicy.FINAL_MARK)), f.sent)
        assertEquals(CheckInPolicy.FINAL_MARK, f.settings.lastCheckInMark)
        assertEquals(1, f.scheduler.cancelCount)
    }
}
