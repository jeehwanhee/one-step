package com.jeepark.onestep.domain.service

import com.jeepark.onestep.domain.model.Coordinates
import com.jeepark.onestep.domain.model.Place
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

// 사용자 위치에서 가까운 장소 찾기 (순수 함수, Android·네트워크 의존 없음).

/** 카테고리별 거리 정책 (단위: m). [primaryRadius] 이내를 우선하고, 없으면 [fallbackRadius]까지 넓힌다. */
private data class PlacePolicy(val primaryRadius: Double, val fallbackRadius: Double)

private val POLICIES = mapOf(
    "park"          to PlacePolicy(3_000.0,  12_000.0),  // 공원: 도보 가능 + 외곽 여유
    "library"       to PlacePolicy(3_000.0,  12_000.0),  // 도서관
    "gym"           to PlacePolicy(3_000.0,  15_000.0),  // 체육시설 (외곽 희소)
    "youth_space"   to PlacePolicy(10_000.0, 30_000.0),  // 청년공간 (희소)
    "mental_center" to PlacePolicy(15_000.0, 50_000.0),  // 정신건강센터
)
private val DEFAULT_POLICY = PlacePolicy(5_000.0, 20_000.0)

private const val EARTH_RADIUS_METERS = 6_371_000.0

/**
 * [from]에서 [type] 종류의 가장 가까운 장소를 고른다.
 * - 가까운 반경([PlacePolicy.primaryRadius]) 안에 여럿이면 다양성을 위해 [random]으로 하나를 고른다
 * - 그 밖이라도 넓은 반경 안에 있으면 가장 가까운 곳
 * - 둘 다 없으면 종류가 같은 곳 중 가장 가까운 한 곳
 * - 위치를 모르거나(null) 그 종류의 장소가 하나도 없으면 null
 */
fun findNearestPlace(type: String, places: List<Place>, from: Coordinates?, random: Random): Place? {
    if (from == null) return null

    val candidates = places.filter { it.type == type }
    if (candidates.isEmpty()) return null

    val withDistance = candidates.map { it to distanceMeters(from, Coordinates(it.lat, it.lng)) }
    val policy = POLICIES[type] ?: DEFAULT_POLICY

    val nearby = withDistance.filter { it.second <= policy.primaryRadius }
    if (nearby.isNotEmpty()) return nearby.random(random).first

    val mid = withDistance.filter { it.second <= policy.fallbackRadius }
    if (mid.isNotEmpty()) return mid.minByOrNull { it.second }?.first

    return withDistance.minByOrNull { it.second }?.first
}

/** 두 지점 사이의 거리(m). haversine 공식. */
fun distanceMeters(a: Coordinates, b: Coordinates): Double {
    val dLat = Math.toRadians(b.lat - a.lat)
    val dLng = Math.toRadians(b.lng - a.lng)
    val h = sin(dLat / 2).pow(2) +
            cos(Math.toRadians(a.lat)) * cos(Math.toRadians(b.lat)) * sin(dLng / 2).pow(2)
    return EARTH_RADIUS_METERS * 2 * atan2(sqrt(h), sqrt(1 - h))
}
