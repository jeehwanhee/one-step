package com.jeepark.onestep.data.repository

import android.content.Context
import com.jeepark.onestep.data.model.Place
import com.jeepark.onestep.util.LocationHelper
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

object PlaceRepository {

    /** 카테고리별 거리 정책 (단위: m). primaryRadius 이내 우선, 없으면 fallbackRadius. */
    private data class PlacePolicy(val primaryRadius: Double, val fallbackRadius: Double)

    private val POLICIES = mapOf(
        "park"          to PlacePolicy(3_000.0,  12_000.0),  // 공원: 도보 가능 + 외곽 여유
        "library"       to PlacePolicy(3_000.0,  12_000.0),  // 도서관
        "gym"           to PlacePolicy(3_000.0,  15_000.0),  // 체육시설 (외곽 희소)
        "youth_space"   to PlacePolicy(10_000.0, 30_000.0),  // 청년공간 (희소)
        "mental_center" to PlacePolicy(15_000.0, 50_000.0),  // 정신건강센터
    )
    private val DEFAULT_POLICY = PlacePolicy(5_000.0, 20_000.0)

    @Volatile private var versionedCache: VersionedCache<Place>? = null

    private fun cache(context: Context): VersionedCache<Place> {
        versionedCache?.let { return it }
        synchronized(this) {
            versionedCache?.let { return it }
            val newCache = VersionedCache(
                context = context.applicationContext,
                collection = "places",
                metaDocId = "places_meta",
            ) { doc -> try { doc.toObject(Place::class.java) } catch (e: Exception) { null } }
            versionedCache = newCache
            return newCache
        }
    }

    /** 버전 키 + 캐시 기반 로드. */
    suspend fun loadAll(context: Context): List<Place> = cache(context).load()

    /**
     * 사용자 위치에서 가장 가까운 장소 찾기.
     * - primary 이내 다수 → 다양성 위해 무작위 선택
     * - primary 밖, fallback 이내 → 최단 거리
     * - 둘 다 없으면 가장 가까운 1곳
     * - GPS 없으면 null
     */
    fun findNearestPlace(type: String, allPlaces: List<Place>): Place? {
        val lat = LocationHelper.currentLat ?: return null
        val lng = LocationHelper.currentLng ?: return null

        val candidates = allPlaces.filter { it.type == type }
        if (candidates.isEmpty()) return null

        val withDistance = candidates.map { it to haversine(lat, lng, it.lat, it.lng) }
        val policy = POLICIES[type] ?: DEFAULT_POLICY

        val nearby = withDistance.filter { it.second <= policy.primaryRadius }
        if (nearby.isNotEmpty()) return nearby.random().first

        val mid = withDistance.filter { it.second <= policy.fallbackRadius }
        if (mid.isNotEmpty()) return mid.minByOrNull { it.second }?.first

        return withDistance.minByOrNull { it.second }?.first
    }

    private fun haversine(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Double {
        val R = 6371000.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLng = Math.toRadians(lng2 - lng1)
        val a = sin(dLat / 2).pow(2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLng / 2).pow(2)
        return R * 2 * atan2(sqrt(a), sqrt(1 - a))
    }
}
