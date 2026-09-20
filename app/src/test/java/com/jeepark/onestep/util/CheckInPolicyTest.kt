package com.jeepark.onestep.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test

class CheckInPolicyTest {

    private val bannedWords = listOf("울고", "쌓이", "마지막", "내일이", "오늘 딱", "퀘스트", "경험치")

    @Test
    fun `접속 다음날까지는 안부를 보내지 않는다`() {
        assertNull(CheckInPolicy.latestDueMark(daysSince = 0, lastSentMark = 0))
        assertNull(CheckInPolicy.latestDueMark(daysSince = 1, lastSentMark = 0))
    }

    @Test
    fun `2일째에 첫 안부 단계가 된다`() {
        assertEquals(2, CheckInPolicy.latestDueMark(daysSince = 2, lastSentMark = 0))
    }

    @Test
    fun `이미 보낸 단계는 다시 보내지 않는다`() {
        assertNull(CheckInPolicy.latestDueMark(daysSince = 2, lastSentMark = 2))
        assertNull(CheckInPolicy.latestDueMark(daysSince = 4, lastSentMark = 2))
    }

    @Test
    fun `워커가 늦게 실행돼도 놓친 단계를 보낸다`() {
        assertEquals(5, CheckInPolicy.latestDueMark(daysSince = 6, lastSentMark = 2))
    }

    @Test
    fun `오래 지나 실행되면 가장 큰 단계 하나만 보낸다`() {
        assertEquals(30, CheckInPolicy.latestDueMark(daysSince = 45, lastSentMark = 0))
    }

    @Test
    fun `마지막 단계 이후에는 보내지 않는다`() {
        assertNull(CheckInPolicy.latestDueMark(daysSince = 45, lastSentMark = 30))
    }

    @Test
    fun `안부 문구에는 압박 표현이 없다`() {
        CheckInPolicy.MESSAGES.values.forEach { message ->
            val text = message.title + message.content
            bannedWords.forEach { word ->
                assertFalse("'${message.title}'에 '$word' 포함", text.contains(word))
            }
        }
    }
}
