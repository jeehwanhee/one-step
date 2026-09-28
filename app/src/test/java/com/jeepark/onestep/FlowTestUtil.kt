package com.jeepark.onestep

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runCurrent

/**
 * [flow]가 내보내는 값을 테스트가 끝날 때까지 모아서 돌려준다(일회성 이벤트 검증용).
 * 수집은 즉시 시작되므로, 수집을 시작하기 전에 이미 보낸 이벤트도 함께 받는다(버퍼링되는 채널 기반 흐름).
 */
@OptIn(ExperimentalCoroutinesApi::class)
fun <T> TestScope.collectEvents(flow: Flow<T>): MutableList<T> {
    val events = mutableListOf<T>()
    backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) { flow.toList(events) }
    runCurrent()
    return events
}
