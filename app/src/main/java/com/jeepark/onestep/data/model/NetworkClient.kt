package com.jeepark.onestep.data.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    // 서울 실시간 도시데이터 (8088 포트, HTTP 전용)
    private const val SEOUL_URL = "http://openapi.seoul.go.kr:8088/"
    // 기상청 단기예보 (전국 커버, HTTPS)
    private const val KMA_URL = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/"

    val apiService: ApiService = Retrofit.Builder()
        .baseUrl(SEOUL_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    val kmaService: KmaService = Retrofit.Builder()
        .baseUrl(KMA_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(KmaService::class.java)
}
