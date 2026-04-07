package com.jeepark.onestep.data.model

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{apiKey}/json/citydata/1/1/{areaName}")
    suspend fun getRealtimeCityData(
        @Path("apiKey") apiKey: String,
        @Path("areaName") areaName: String
    ): SeoulCityDataResponse
}