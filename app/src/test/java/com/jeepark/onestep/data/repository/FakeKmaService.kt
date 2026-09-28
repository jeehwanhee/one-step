package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.KmaResponse
import com.jeepark.onestep.data.model.KmaService

/** 테스트용 가짜 KmaService. 받은 요청을 기록하고, 정해 둔 응답이나 예외를 돌려준다. */
class FakeKmaService : KmaService {

    data class Call(
        val serviceKey: String,
        val baseDate: String,
        val baseTime: String,
        val nx: Int,
        val ny: Int,
    )

    var response: KmaResponse = KmaResponse(response = null)
    var error: Throwable? = null
    val calls = mutableListOf<Call>()

    override suspend fun getUltraSrtNcst(
        serviceKey: String,
        dataType: String,
        numOfRows: Int,
        pageNo: Int,
        baseDate: String,
        baseTime: String,
        nx: Int,
        ny: Int,
    ): KmaResponse {
        calls.add(Call(serviceKey, baseDate, baseTime, nx, ny))
        error?.let { throw it }
        return response
    }
}
