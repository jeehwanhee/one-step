package com.jeepark.onestep.data.model

data class Place(
    val type: String = "",       // park, library, ...
    val name: String = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val district: String = "",
)
