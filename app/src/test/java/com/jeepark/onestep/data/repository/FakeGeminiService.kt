package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.GeminiRequest
import com.jeepark.onestep.data.model.GeminiResponse
import com.jeepark.onestep.data.model.GeminiService

/** 테스트용 가짜 GeminiService. 받은 요청을 기록하고, 정해 둔 응답이나 예외를 돌려준다. */
class FakeGeminiService : GeminiService {

    var response: GeminiResponse = GeminiResponse(candidates = null)
    var error: Throwable? = null

    var lastApiKey: String? = null
        private set
    var lastRequest: GeminiRequest? = null
        private set

    override suspend fun generateContent(apiKey: String, request: GeminiRequest): GeminiResponse {
        lastApiKey = apiKey
        lastRequest = request
        error?.let { throw it }
        return response
    }
}
