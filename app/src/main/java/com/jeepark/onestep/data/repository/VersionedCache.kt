package com.jeepark.onestep.data.repository

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.tasks.await

/**
 * 버전 키 기반 Firestore 캐싱 도우미.
 * - meta/{key}_meta 문서의 version 필드를 1 read로 확인
 * - 로컬 저장된 version과 같으면 → 캐시 사용 (네트워크 X)
 * - 다르면 → 전체 컬렉션 다운로드 + 로컬에 저장
 */
class VersionedCache<T>(
    private val context: Context,
    private val collection: String,           // 예: "quests", "places"
    private val metaDocId: String,            // 예: "quests_meta", "places_meta"
    private val parser: (com.google.firebase.firestore.DocumentSnapshot) -> T?,
) {
    private val db = Firebase.firestore
    private val prefs = context.getSharedPreferences("versioned_cache", Context.MODE_PRIVATE)
    private val mutex = Mutex()

    @Volatile private var memoryCache: List<T>? = null

    private val versionKey = "${collection}_version"

    /** 서버 버전과 로컬 버전을 비교, 캐시 또는 새로 다운로드. */
    suspend fun load(): List<T> = mutex.withLock {
        memoryCache?.let { return@withLock it }

        val localVersion = prefs.getLong(versionKey, -1L)
        val serverVersion = try {
            db.collection("meta").document(metaDocId).get().await().getLong("version") ?: -1L
        } catch (e: Exception) {
            // 메타 조회 실패 → 디스크 캐시 fallback
            return@withLock loadFromDiskOrServer()
        }

        // 같은 버전 + 디스크 캐시 사용 가능
        if (localVersion == serverVersion && localVersion != -1L) {
            val disk = loadFromDisk()
            if (disk != null) {
                memoryCache = disk
                return@withLock disk
            }
        }

        // 다른 버전 → 전체 재다운로드
        val list = downloadAll()
        memoryCache = list
        // 로컬 저장
        if (serverVersion != -1L) {
            prefs.edit().putLong(versionKey, serverVersion).apply()
        }
        return@withLock list
    }

    /** 강제 새로고침 (메모리 캐시 무효화). */
    suspend fun refresh(): List<T> = mutex.withLock {
        memoryCache = null
        prefs.edit().remove(versionKey).apply()
        val list = downloadAll()
        memoryCache = list
        list
    }

    private suspend fun downloadAll(): List<T> {
        val snapshot = db.collection(collection).get().await()
        return snapshot.documents.mapNotNull { doc ->
            try { parser(doc) } catch (e: Exception) { null }
        }
    }

    private suspend fun loadFromDiskOrServer(): List<T> {
        val disk = loadFromDisk()
        if (disk != null) {
            memoryCache = disk
            return disk
        }
        val list = downloadAll()
        memoryCache = list
        return list
    }

    /**
     * Firestore SDK가 자체 디스크 캐싱을 하므로,
     * Source.CACHE로 조회하면 마지막 다운로드 결과를 반환합니다.
     */
    private suspend fun loadFromDisk(): List<T>? = try {
        val snapshot = db.collection(collection)
            .get(com.google.firebase.firestore.Source.CACHE).await()
        if (snapshot.isEmpty) null
        else snapshot.documents.mapNotNull { doc ->
            try { parser(doc) } catch (e: Exception) { null }
        }
    } catch (e: Exception) { null }
}
