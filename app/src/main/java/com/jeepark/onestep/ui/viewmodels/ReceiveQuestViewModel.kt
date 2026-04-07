package com.jeepark.onestep.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeepark.onestep.data.model.NetworkClient
import com.jeepark.onestep.data.model.SeoulCityDataResponse
import kotlinx.coroutines.launch
import com.jeepark.onestep.BuildConfig

class ReceiveQuestViewModel : ViewModel() {
    var selectedMood by mutableStateOf("")
    var isInside by mutableStateOf(true)
    var isLoading by mutableStateOf(false)
    var apiResponse by mutableStateOf<SeoulCityDataResponse?>(null)

    val isButtonEnabled: Boolean
        get() = selectedMood.isNotEmpty()

    fun onMoodSelected(mood: String) {
        selectedMood = mood
    }

    fun toggleLocation(inside: Boolean) {
        isInside = inside
    }

    fun fetchQuestData(onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = NetworkClient.apiService.getRealtimeCityData(
                    apiKey = BuildConfig.SEOUL_API_KEY,
                    areaName = "건대입구역"
                )

                if (response.CITYDATA != null) {
                    apiResponse = response
                    onSuccess()
                } else {
                    android.util.Log.e("API_ERROR", "데이터가 비어있습니다. 키를 확인하세요.")
                }
            } catch (e: Exception) {
                android.util.Log.e("API_ERROR", "통신 실패: ${e.message}")
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}