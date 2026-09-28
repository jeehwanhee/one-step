package com.jeepark.onestep.data.repository

import android.content.SharedPreferences
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.jeepark.onestep.data.model.FirestorePaths
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.tasks.await

/** 버전이 기록돼 있지 않음을 뜻하는 값. */
const val NO_VERSION = -1L

/** 서버에서 통째로 내려받는 컬렉션 하나. [VersionedCache]가 캐시 판단에 필요한 세 가지 동작. */
interface CollectionSource<T> {
    /** 서버의 데이터 버전. 서버에 버전 정보가 없으면 null이고, 서버에 닿지 못하면 예외를 던진다. */
    suspend fun fetchServerVersion(): Long?

    /** 서버에서 컬렉션 전체를 내려받는다. 실패하면 예외를 던진다. */
    suspend fun downloadAll(): List<T>

    /** 기기에 남아 있는 마지막 다운로드본. 없거나 읽을 수 없으면 null. */
    suspend fun readLocalCopy(): List<T>?
}

/** 마지막으로 내려받은 데이터의 버전을 기기에 기억한다. */
interface VersionStore {
    /** 기억해 둔 버전. 없으면 [NO_VERSION]. */
    fun read(): Long
    fun write(version: Long)
}

/**
 * 버전 키 기반 캐싱.
 * - 서버의 버전을 1 read로 확인
 * - 기기에 기억한 버전과 같고 로컬 사본이 있으면 → 캐시 사용 (전체 다운로드 없음)
 * - 다르면 → 전체 다운로드 후 버전 기억
 * - 서버 버전을 확인하지 못하면 → 로컬 사본, 없으면 전체 다운로드
 * 한 번 불러온 결과는 메모리에 들고 있어서 이후에는 어떤 조회도 하지 않는다.
 */
class VersionedCache<T>(
    private val source: CollectionSource<T>,
    private val versions: VersionStore,
) {
    private val mutex = Mutex()

    @Volatile private var memoryCache: List<T>? = null

    suspend fun load(): List<T> = mutex.withLock {
        memoryCache?.let { return@withLock it }

        val localVersion = versions.read()
        val serverVersion = try {
            source.fetchServerVersion() ?: NO_VERSION
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            // 버전 확인 실패 → 로컬 사본 fallback
            return@withLock loadFromLocalOrServer()
        }

        // 같은 버전 + 로컬 사본 사용 가능
        if (localVersion == serverVersion && localVersion != NO_VERSION) {
            val local = source.readLocalCopy()
            if (local != null) {
                memoryCache = local
                return@withLock local
            }
        }

        // 다른 버전 → 전체 재다운로드
        val list = source.downloadAll()
        memoryCache = list
        if (serverVersion != NO_VERSION) versions.write(serverVersion)
        list
    }

    private suspend fun loadFromLocalOrServer(): List<T> {
        val list = source.readLocalCopy() ?: source.downloadAll()
        memoryCache = list
        return list
    }
}

/** Firestore 컬렉션 하나와 그 버전 문서(`meta/{metaDocId}`의 `version` 필드, [FirestorePaths])를 읽는 구현. */
class FirestoreCollectionSource<T>(
    private val db: FirebaseFirestore,
    private val collection: String,     // 예: "quests", "places"
    private val metaDocId: String,      // 예: "quests_meta", "places_meta"
    private val parser: (DocumentSnapshot) -> T?,
) : CollectionSource<T> {

    override suspend fun fetchServerVersion(): Long? =
        db.collection(FirestorePaths.META).document(metaDocId).get().await().getLong(FirestorePaths.META_VERSION_FIELD)

    override suspend fun downloadAll(): List<T> =
        db.collection(collection).get().await().documents.mapNotNull(::parseOrNull)

    /**
     * Firestore SDK가 자체 디스크 캐싱을 하므로,
     * Source.CACHE로 조회하면 마지막 다운로드 결과를 반환한다.
     */
    override suspend fun readLocalCopy(): List<T>? = try {
        val snapshot = db.collection(collection).get(Source.CACHE).await()
        if (snapshot.isEmpty) null else snapshot.documents.mapNotNull(::parseOrNull)
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        null
    }

    // 모델로 바꿀 수 없는 문서 하나 때문에 컬렉션 전체가 실패하지 않도록 그 문서만 건너뛴다
    private fun parseOrNull(doc: DocumentSnapshot): T? = try {
        parser(doc)
    } catch (e: Exception) {
        null
    }
}

/**
 * SharedPreferences 구현. 파일 이름과 키는 이미 설치된 기기에 저장된 버전을 읽어야 하므로 바꾸면 안 된다.
 * 버전을 잃으면 다음 실행 때 컬렉션을 한 번 더 내려받게 될 뿐이지만, 이름이 바뀌면 그런 일이 모든 사용자에게 생긴다.
 */
class SharedPrefsVersionStore(
    private val prefs: SharedPreferences,
    private val key: String,
) : VersionStore {

    override fun read(): Long = prefs.getLong(key, NO_VERSION)

    override fun write(version: Long) {
        prefs.edit().putLong(key, version).apply()
    }

    companion object {
        const val FILE_NAME = "versioned_cache"

        /** 컬렉션 이름으로 만드는 버전 키. 예: "quests" → "quests_version". */
        fun keyFor(collection: String): String = "${collection}_version"
    }
}
