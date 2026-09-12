package com.jeepark.onestep.data.model

import retrofit2.http.GET
import retrofit2.http.Query

// 기상청 초단기실황 응답 구조
data class KmaResponse(val response: KmaResponseBody?)
data class KmaResponseBody(val body: KmaBody?)
data class KmaBody(val items: KmaItems?)
data class KmaItems(val item: List<KmaItem>?)
data class KmaItem(
    val category: String = "",   // T1H(기온), PTY(강수형태), RN1(강수량), REH(습도) 등
    val obsrValue: String = "",  // 관측값
)

interface KmaService {
    // 초단기실황: 현재 시각 관측값 (전국 격자 좌표 기반)
    @GET("getUltraSrtNcst")
    suspend fun getUltraSrtNcst(
        @Query("serviceKey") serviceKey: String,
        @Query("dataType") dataType: String = "JSON",
        @Query("numOfRows") numOfRows: Int = 60,
        @Query("pageNo") pageNo: Int = 1,
        @Query("base_date") baseDate: String,
        @Query("base_time") baseTime: String,
        @Query("nx") nx: Int,
        @Query("ny") ny: Int,
    ): KmaResponse
}
