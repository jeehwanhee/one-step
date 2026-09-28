package com.jeepark.onestep.data.repository

/**
 * 테스트용 가짜 CollectionSource. 서버 버전·다운로드·로컬 사본을 마음대로 정하고, 각 호출이 몇 번 일어났는지 센다.
 * [downloadGate]를 설정하면 다운로드가 그 게이트가 열릴 때까지 끝나지 않는다(동시 호출 검증용).
 */
class FakeCollectionSource<T>(
    var serverVersion: Long? = 1L,
    var items: List<T> = emptyList(),
    var localCopy: List<T>? = null,
) : CollectionSource<T> {

    var versionError: Exception? = null
    var downloadError: Exception? = null
    var downloadGate: kotlinx.coroutines.CompletableDeferred<Unit>? = null

    var versionCalls = 0
        private set
    var downloadCalls = 0
        private set
    var localReadCalls = 0
        private set

    override suspend fun fetchServerVersion(): Long? {
        versionCalls++
        versionError?.let { throw it }
        return serverVersion
    }

    override suspend fun downloadAll(): List<T> {
        downloadCalls++
        downloadGate?.await()
        downloadError?.let { throw it }
        return items
    }

    override suspend fun readLocalCopy(): List<T>? {
        localReadCalls++
        return localCopy
    }
}
