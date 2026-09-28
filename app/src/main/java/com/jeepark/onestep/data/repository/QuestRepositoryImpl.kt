package com.jeepark.onestep.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import com.jeepark.onestep.domain.model.FirestorePaths
import com.jeepark.onestep.domain.model.GiveUpReason
import com.jeepark.onestep.domain.model.Quest
import com.jeepark.onestep.domain.model.QuestFields
import com.jeepark.onestep.domain.service.SAMPLE_SIZE
import com.jeepark.onestep.domain.service.findNearestPlace
import com.jeepark.onestep.domain.service.hasPlaceholders
import com.jeepark.onestep.domain.service.randomSelection
import com.jeepark.onestep.domain.service.resolvePlaceholders
import com.jeepark.onestep.domain.service.sampleByRatio
import com.jeepark.onestep.platform.location.LocationProvider
import kotlinx.coroutines.CancellationException
import kotlin.random.Random

/**
 * 퀘스트 후보를 모아 사용자에게 보여줄 목록을 만든다. 각 단계의 규칙은 순수 함수(QuestSelection·PlaceholderResolver 등)에,
 * 외부 접근은 주입받은 구성요소(캐시·장소·위치·날씨·추천)에 있고, 이 클래스는 순서를 조립한다.
 */
class QuestRepositoryImpl(
    private val questsCache: VersionedCache<Quest>,
    private val places: PlaceRepository,
    private val location: LocationProvider,
    private val weather: WeatherProvider,
    private val ranker: QuestRanker,
    private val random: Random = Random.Default,
) : QuestRepository {

    // 포기 사유 기록에만 쓰므로 그 기능을 실제로 쓸 때까지 Firebase에 접근하지 않는다
    private val db by lazy { Firebase.firestore }

    override suspend fun fetchFilteredQuests(
        ratios: List<Double>,
        useGemini: Boolean
    ): List<Quest> {
        // 1. quests 컬렉션 로드 (버전 키 캐싱)
        val allQuests = questsCache.load().filter { it.questName.isNotEmpty() }
        if (allQuests.isEmpty()) throw Exception("quests 컬렉션이 비어 있습니다")

        // 2. 비율에 맞게 SAMPLE_SIZE개 샘플링
        val sampled = sampleByRatio(allQuests, ratios, SAMPLE_SIZE, random)

        // 3. {공원}/{도서관} 등 플레이스홀더를 사용자 위치 기반 실제 장소명으로 치환
        val substituted = substitutePlaceholders(sampled)

        // 4. 일일 한도 초과 시 추천 서비스를 건너뛰고 무작위로 고른다
        if (!useGemini) return randomSelection(substituted, random)

        // 5. 날씨 조회 (실패해도 "정보 없음"으로 계속 진행)
        val weatherText = weather.describeCurrentWeather()

        // 6. 추천 서비스로 선별 (실패하면 무작위 선택으로 대신)
        return try {
            ranker.select(substituted, weatherText)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            android.util.Log.e("QuestRepo", "추천 선별 실패, 무작위로 대신함", e)
            randomSelection(substituted, random)
        }
    }

    private suspend fun substitutePlaceholders(quests: List<Quest>): List<Quest> {
        // 어떤 quest에라도 플레이스홀더가 있는 경우만 places 로드
        if (!hasPlaceholders(quests)) return quests

        val allPlaces = try {
            places.loadAll()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            return quests
        }

        return resolvePlaceholders(quests) { type ->
            findNearestPlace(type, allPlaces, location.current, random)
        }
    }

    /** 해당 퀘스트 문서에 포기 사유를 기록 (통계용, 실패해도 유저 쪽 기록에는 영향 없음). */
    override fun recordGiveUp(questIndex: Int, reason: GiveUpReason, onFailure: (Exception) -> Unit) {
        db.collection(FirestorePaths.QUESTS)
            .whereEqualTo(QuestFields.INDEX, questIndex)
            .get()
            .addOnSuccessListener { snapshot ->
                snapshot.documents.firstOrNull()?.reference?.update(
                    QuestFields.GIVE_UP_REASONS, FieldValue.arrayUnion(reason.code)
                )?.addOnFailureListener { onFailure(it) }
            }.addOnFailureListener { onFailure(it) }
    }
}
