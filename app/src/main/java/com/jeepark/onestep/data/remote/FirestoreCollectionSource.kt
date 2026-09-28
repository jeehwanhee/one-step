package com.jeepark.onestep.data.remote

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.jeepark.onestep.data.repository.CollectionSource
import com.jeepark.onestep.domain.model.FirestorePaths
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

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
