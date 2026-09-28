package com.jeepark.onestep.data.repository

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
    fun `서버 버전이 다르면 사본이 있어도 다시 내려받고 새 버전을 기억한다`() = runTest {
        val f = Fixture(serverVersion = 6L, localVersion = 5L, items = serverItems, localCopy = localItems)

        val result = f.cache.load()

        assertEquals(serverItems, result)
        assertEquals(1, f.source.downloadCalls)
        assertEquals(listOf(6L), f.versions.writes)
        assertEquals(0, f.source.localReadCalls) // 어차피 내려받을 것이므로 사본은 읽지 않는다
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
}
