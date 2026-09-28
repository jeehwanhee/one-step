package com.jeepark.onestep.data.repository

import com.jeepark.onestep.domain.model.Place

/** 테스트용 가짜 PlaceRepository. */
class FakePlaceRepository(var places: List<Place> = emptyList()) : PlaceRepository {

    var error: Exception? = null

    var loadCount = 0
        private set

    override suspend fun loadAll(): List<Place> {
        loadCount++
        error?.let { throw it }
        return places
    }
}
