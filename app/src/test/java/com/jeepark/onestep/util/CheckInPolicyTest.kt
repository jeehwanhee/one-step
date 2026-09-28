package com.jeepark.onestep.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test

class CheckInPolicyTest {

    private val bannedWords = listOf("울고", "쌓이", "마지막", "내일이", "오늘 딱", "퀘스트", "경험치")

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
