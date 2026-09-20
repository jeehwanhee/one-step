package com.jeepark.onestep.util

object CheckInPolicy {

    data class Message(val title: String, val content: String)

    val MESSAGES: Map<Int, Message> = mapOf(
        2 to Message("잘 지내고 있나요?", "부담 없이, 생각날 때 들러주세요"),
        5 to Message("동물 친구들이 안부를 전해요", "오늘은 쉬어가는 날이어도 괜찮아요"),
        10 to Message("천천히 와도 괜찮아요", "언제든 여기 있을게요"),
        20 to Message("오랜만이에요", "편할 때 열어보세요"),
        30 to Message("여전히 여기 있어요", "필요할 때 언제든 열어보세요"),
    )

    val DAY_MARKS: List<Int> = MESSAGES.keys.sorted()
    val FINAL_MARK: Int = DAY_MARKS.last()

    // 배터리 절전으로 워커가 밀려 실행돼도 안부가 연달아 나가지 않도록, 아직 안 보낸 단계 중 가장 큰 것 하나만 고른다
    fun latestDueMark(daysSince: Int, lastSentMark: Int): Int? =
        DAY_MARKS.filter { it > lastSentMark && it <= daysSince }.maxOrNull()
}
