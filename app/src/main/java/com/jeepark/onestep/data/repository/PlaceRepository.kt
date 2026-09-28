package com.jeepark.onestep.data.repository

import com.jeepark.onestep.domain.model.Place

/** 퀘스트 문구에 넣을 장소(공원·도서관 등) 목록. 테스트에서는 FakePlaceRepository로 교체할 수 있다. */
interface PlaceRepository {
    /** 모든 장소. 서버 버전이 같으면 기기에 저장된 사본을 쓴다. 불러오지 못하면 예외를 던진다. */
    suspend fun loadAll(): List<Place>
}

class PlaceRepositoryImpl(private val cache: VersionedCache<Place>) : PlaceRepository {
    override suspend fun loadAll(): List<Place> = cache.load()
}
