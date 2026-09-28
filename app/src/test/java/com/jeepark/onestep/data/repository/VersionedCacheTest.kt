package com.jeepark.onestep.data.repository

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

class VersionedCacheTest {

    private val serverItems = listOf("서버A", "서버B")
    private val localItems = listOf("로컬A")

    private class Fixture(
        serverVersion: Long? = 5L,
        localVersion: Long = NO_VERSION,
        items: List<String>,
        localCopy: List<String>? = null,
    ) {
        val source = FakeCollectionSource(serverVersion = serverVersion, items = items, localCopy = localCopy)
        val versions = FakeVersionStore(localVersion)
        val cache = VersionedCache(source, versions)
    }

    // ===== 서버 버전과 비교 =====

    @Test
    fun `처음 불러오면 전체를 내려받고 서버 버전을 기억한다`() = runTest {
        val f = Fixture(serverVersion = 5L, items = serverItems)

        val result = f.cache.load()

        assertEquals(serverItems, result)
        assertEquals(1, f.source.downloadCalls)
        assertEquals(listOf(5L), f.versions.writes)
    }

    @Test
    fun `서버 버전이 기억한 버전과 같고 로컬 사본이 있으면 내려받지 않고 사본을 쓴다`() = runTest {
        val f = Fixture(serverVersion = 5L, localVersion = 5L, items = serverItems, localCopy = localItems)

        val result = f.cache.load()

        assertEquals(localItems, result)
        assertEquals(0, f.source.downloadCalls)
        assertTrue(f.versions.writes.isEmpty())
    }

    @Test
    fun `버전이 같아도 로컬 사본이 없으면 다시 내려받는다`() = runTest {
        val f = Fixture(serverVersion = 5L, localVersion = 5L, items = serverItems, localCopy = null)

        val result = f.cache.load()

        assertEquals(serverItems, result)
        assertEquals(1, f.source.downloadCalls)
    }

    @Test
    fun `서버 버전이 다르면 사본이 있어도 다시 내려받고 새 버전을 기억한다`() = runTest {
        val f = Fixture(serverVersion = 6L, localVersion = 5L, items = serverItems, localCopy = localItems)

        val result = f.cache.load()

        assertEquals(serverItems, result)
        assertEquals(1, f.source.downloadCalls)
        assertEquals(listOf(6L), f.versions.writes)
        assertEquals(0, f.source.localReadCalls) // 어차피 내려받을 것이므로 사본은 읽지 않는다
    }

    @Test
    fun `서버에 버전 정보가 없으면 내려받되 버전은 기억하지 않는다`() = runTest {
        val f = Fixture(serverVersion = null, localVersion = NO_VERSION, items = serverItems, localCopy = localItems)

        val result = f.cache.load()

        // 기억한 버전도 없음이라 "같은 버전"으로 착각해 사본을 쓰지 않는다
        assertEquals(serverItems, result)
        assertEquals(1, f.source.downloadCalls)
        assertTrue(f.versions.writes.isEmpty())
    }

    // ===== 서버 버전을 확인하지 못할 때 =====

    @Test
    fun `버전 확인에 실패하면 로컬 사본을 쓴다`() = runTest {
        val f = Fixture(items = serverItems, localCopy = localItems)
        f.source.versionError = java.io.IOException("오프라인")

        val result = f.cache.load()

        assertEquals(localItems, result)
        assertEquals(0, f.source.downloadCalls)
    }

    @Test
    fun `버전 확인에 실패하고 로컬 사본도 없으면 내려받는다`() = runTest {
        val f = Fixture(items = serverItems, localCopy = null)
        f.source.versionError = java.io.IOException("오프라인")

        val result = f.cache.load()

        assertEquals(serverItems, result)
        assertEquals(1, f.source.downloadCalls)
        assertTrue(f.versions.writes.isEmpty()) // 서버 버전을 모르니 기억할 것도 없다
    }

    // ===== 실패와 재시도 =====

    @Test
    fun `내려받기에 실패하면 예외를 그대로 던지고 버전은 기억하지 않는다`() = runTest {
        val f = Fixture(items = serverItems)
        f.source.downloadError = java.io.IOException("네트워크 오류")

        try {
            f.cache.load()
            fail("예외가 던져져야 한다")
        } catch (e: java.io.IOException) {
            assertEquals("네트워크 오류", e.message)
        }
        assertTrue(f.versions.writes.isEmpty())
    }

    @Test
    fun `실패한 뒤에는 다음 호출에서 다시 시도한다`() = runTest {
        val f = Fixture(items = serverItems)
        f.source.downloadError = java.io.IOException("네트워크 오류")
        runCatching { f.cache.load() }

        f.source.downloadError = null
        val result = f.cache.load()

        assertEquals(serverItems, result)
        assertEquals(2, f.source.downloadCalls)
    }

    // ===== 메모리 캐시와 동시 호출 =====

    @Test
    fun `한 번 불러온 뒤에는 어떤 조회도 하지 않는다`() = runTest {
        val f = Fixture(items = serverItems)
        f.cache.load()

        val second = f.cache.load()
        val third = f.cache.load()

        assertEquals(serverItems, second)
        assertEquals(serverItems, third)
        assertEquals(1, f.source.versionCalls)
        assertEquals(1, f.source.downloadCalls)
    }

    @Test
    fun `동시에 여러 번 불러도 내려받기는 한 번만 한다`() = runTest {
        val f = Fixture(items = serverItems)
        val gate = CompletableDeferred<Unit>()
        f.source.downloadGate = gate

        val calls = (1..3).map { async { f.cache.load() } }
        gate.complete(Unit)
        val results = calls.awaitAll()

        assertTrue(results.all { it == serverItems })
        assertEquals(1, f.source.downloadCalls)
    }

    // ===== 취소 =====

    @Test
    fun `코루틴 취소는 오프라인 실패로 착각하지 않고 그대로 전파한다`() = runTest {
        val f = Fixture(items = serverItems, localCopy = localItems)
        f.source.versionError = CancellationException("취소됨")

        val failure = runCatching { f.cache.load() }.exceptionOrNull()

        assertNotNull(failure)
        assertTrue(failure is CancellationException)
        assertEquals(0, f.source.localReadCalls) // 로컬 사본으로 넘어가지 않는다
    }
}
