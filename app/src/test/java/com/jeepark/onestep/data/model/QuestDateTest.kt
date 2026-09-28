package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
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
    fun `완료 시각은 yyyy MM dd HH mm ss 형식이다`() {
        // 2026-09-28 01:00:05 UTC = 서울 10:00:05
        val clock = clockAt("2026-09-28T01:00:05Z")

        assertEquals("2026.09.28 10:00:05", QuestDate.doneDateNow(clock))
    }

    @Test
    fun `월 일 시 분 초가 한 자리여도 0으로 채워 고정폭이다`() {
        val clock = clockAt("2026-01-02T03:04:05Z", ZoneOffset.UTC)

        val formatted = QuestDate.doneDateNow(clock)

        assertEquals("2026.01.02 03:04:05", formatted)
        assertEquals(QuestDate.DONE_PATTERN.length, formatted.length)
    }

    @Test
    fun `오늘 키는 yyyy-MM-dd 형식이다`() {
        assertEquals("2026-09-28", QuestDate.todayKey(clockAt("2026-09-28T01:00:05Z")))
    }

    @Test
    fun `같은 순간이라도 시간대에 따라 날짜가 갈린다`() {
        val instant = "2026-09-27T15:30:00Z" // 서울은 이미 28일 00:30

        assertEquals("2026-09-28", QuestDate.todayKey(clockAt(instant, seoul)))
        assertEquals("2026-09-27", QuestDate.todayKey(clockAt(instant, ZoneOffset.UTC)))
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

    // ===== doneDay =====

    @Test
    fun `완료 시각 문자열에서 날짜를 읽는다`() {
        assertEquals(LocalDate.of(2026, 1, 31), QuestDate.doneDay("2026.01.31 23:59:59"))
    }

    @Test
    fun `날짜 부분만 있어도 읽는다`() {
        assertEquals(LocalDate.of(2026, 1, 31), QuestDate.doneDay("2026.01.31"))
    }

    @Test
    fun `형식이 맞지 않는 문자열은 null이다`() {
        listOf("", "abc", "2026-01-31", "2026.13.01 10:00:00", "2026.01", "20260131 10:00").forEach { bad ->
            assertNull("input=$bad", QuestDate.doneDay(bad))
        }
    }

    @Test
    fun `PrevQuest에서 완료한 날짜를 읽는다`() {
        val quest = PrevQuest(doneDate = "2026.02.28 08:00:00")

        assertEquals(LocalDate.of(2026, 2, 28), quest.doneDay())
        assertNull(PrevQuest(doneDate = "").doneDay())
    }

    // ===== 표시용 날짜 =====

    @Test
    fun `표시용 날짜는 완료 시각의 앞 열 글자다`() {
        assertEquals("2026.01.31", QuestDate.dayPart("2026.01.31 23:59:59"))
        assertEquals("", QuestDate.dayPart(""))
    }

    @Test
    fun `날짜를 yyyy MM dd로 표시한다`() {
        assertEquals("2026.03.05", QuestDate.formatDay(LocalDate.of(2026, 3, 5)))
    }

    @Test
    fun `표시용 날짜 형식은 완료 시각의 날짜 부분과 같다`() {
        val clock = clockAt("2026-03-05T01:00:00Z")

        assertEquals(
            QuestDate.dayPart(QuestDate.doneDateNow(clock)),
            QuestDate.formatDay(QuestDate.doneDay(QuestDate.doneDateNow(clock))!!),
        )
    }

    // ===== dayOf =====

    @Test
    fun `epoch millis가 속한 날짜는 시간대 기준이다`() {
        val millis = 1772668800000L // 2026-03-05T00:00:00Z

        assertEquals(LocalDate.of(2026, 3, 5), QuestDate.dayOf(millis, ZoneOffset.UTC))
        assertEquals(LocalDate.of(2026, 3, 5), QuestDate.dayOf(millis, seoul))
        assertEquals(LocalDate.of(2026, 3, 4), QuestDate.dayOf(millis, ZoneOffset.ofHours(-5)))
    }

    @Test
    fun `기본 시간대는 시스템 시간대다`() {
        val millis = 1772668800000L

        assertEquals(QuestDate.dayOf(millis, ZoneId.systemDefault()), QuestDate.dayOf(millis))
    }
}
