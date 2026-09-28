package com.jeepark.onestep.domain.rules

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Locale

class QuestDateTest {

    private val seoul = ZoneId.of("Asia/Seoul")

    private fun clockAt(instant: String, zone: ZoneId = seoul): Clock =
        Clock.fixed(Instant.parse(instant), zone)

    // ===== doneDateNow / todayKey =====

    @Test
    fun `월 일 시 분 초가 한 자리여도 0으로 채워 고정폭이다`() {
        val clock = clockAt("2026-01-02T03:04:05Z", ZoneOffset.UTC)

        val formatted = QuestDate.doneDateNow(clock)

        assertEquals("2026.01.02 03:04:05", formatted)
        assertEquals(QuestDate.DONE_PATTERN.length, formatted.length)
    }

    @Test
    fun `기기 언어가 태국어 불교력이어도 연도와 숫자는 그대로다`() {
        val original = Locale.getDefault()
        try {
            Locale.setDefault(Locale.forLanguageTag("th-TH-u-ca-buddhist-nu-thai"))
            val clock = clockAt("2026-09-28T01:00:05Z")

            assertEquals("2026.09.28 10:00:05", QuestDate.doneDateNow(clock))
            assertEquals("2026-09-28", QuestDate.todayKey(clock))
            assertEquals("2026.09.28", QuestDate.formatDay(LocalDate.of(2026, 9, 28)))
        } finally {
            Locale.setDefault(original)
        }
    }

    @Test
    fun `완료 시각 문자열은 시간순으로 사전순 정렬된다`() {
        val instants = listOf(
            "2025-12-31T23:59:59Z",
            "2026-01-01T00:00:00Z",
            "2026-01-09T09:09:09Z",
            "2026-01-10T00:00:00Z",
            "2026-11-02T13:00:00Z",
        )
        val formatted = instants.map { QuestDate.doneDateNow(clockAt(it, ZoneOffset.UTC)) }

        assertEquals(formatted, formatted.sorted())
    }
}
