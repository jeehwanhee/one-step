package com.jeepark.onestep.data.model

data class Place(
    val type: String = "",       // park, library, gym
    val name: String = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val district: String = "",
    val region: String = "",     // 서울, 경기
)
