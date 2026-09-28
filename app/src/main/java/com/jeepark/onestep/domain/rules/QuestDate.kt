package com.jeepark.onestep.domain.rules

import com.jeepark.onestep.domain.model.PrevQuest
import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

/**
 * 퀘스트 날짜 표현의 단일 출처. 저장 형식은 이미 쌓인 Firestore 데이터와 같아야 하므로 바꾸지 않는다.
 *  - `PrevQuest.doneDate`: [DONE_PATTERN] — 고정폭이라 사전순 정렬이 곧 시간순 정렬이다.
 *  - `User.dailyQuestDate`: [DAY_KEY_PATTERN]
 *
 * 형식 지정에는 항상 [Locale.ROOT]를 쓴다. 기기 언어를 따르면 태국어(불교력)처럼 연도가 달라지거나
 * 아랍어처럼 숫자 문자가 달라져 고정폭·정렬 가정이 깨질 수 있다.
 * 현재 시각은 [Clock]으로 주입받아 테스트에서 고정할 수 있다.
 */
object QuestDate {
    const val DONE_PATTERN = "yyyy.MM.dd HH:mm:ss"
    const val DAY_KEY_PATTERN = "yyyy-MM-dd"
    private const val DAY_LABEL_PATTERN = "yyyy.MM.dd"

    /** [DONE_PATTERN]에서 날짜 부분("yyyy.MM.dd")의 길이. */
    private const val DAY_LABEL_LENGTH = 10

    private val doneFormatter = DateTimeFormatter.ofPattern(DONE_PATTERN, Locale.ROOT)
    private val dayKeyFormatter = DateTimeFormatter.ofPattern(DAY_KEY_PATTERN, Locale.ROOT)
    private val dayLabelFormatter = DateTimeFormatter.ofPattern(DAY_LABEL_PATTERN, Locale.ROOT)

    /** 지금 시각의 `doneDate` 문자열. */
    fun doneDateNow(clock: Clock = Clock.systemDefaultZone()): String =
        LocalDateTime.now(clock).format(doneFormatter)

    /** 오늘 날짜 키(`dailyQuestDate` 형식). */
    fun todayKey(clock: Clock = Clock.systemDefaultZone()): String =
        LocalDate.now(clock).format(dayKeyFormatter)

    /** `doneDate` 문자열의 날짜. 형식이 맞지 않으면 null. */
    fun doneDay(doneDate: String): LocalDate? =
        try {
            LocalDate.parse(dayPart(doneDate), dayLabelFormatter)
        } catch (e: DateTimeParseException) {
            null
        }

    /** `doneDate`의 날짜 부분("yyyy.MM.dd")을 화면 표시용으로 잘라낸다. */
    fun dayPart(doneDate: String): String = doneDate.take(DAY_LABEL_LENGTH)

    /** 날짜를 화면 표시용 "yyyy.MM.dd"로. */
    fun formatDay(date: LocalDate): String = date.format(dayLabelFormatter)

    /** epoch millis가 [zone]에서 속하는 날짜. */
    fun dayOf(epochMillis: Long, zone: ZoneId = ZoneId.systemDefault()): LocalDate =
        Instant.ofEpochMilli(epochMillis).atZone(zone).toLocalDate()
}

/** 완료한 날짜. `doneDate` 형식이 맞지 않으면 null. */
fun PrevQuest.doneDay(): LocalDate? = QuestDate.doneDay(doneDate)
