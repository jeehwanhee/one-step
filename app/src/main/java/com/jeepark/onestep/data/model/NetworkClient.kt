package com.jeepark.onestep.data.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    // 기상청 단기예보 (전국 커버, HTTPS)
    private const val KMA_URL = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/"

    val kmaService: KmaService = Retrofit.Builder()
        .baseUrl(KMA_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(KmaService::class.java)
}
