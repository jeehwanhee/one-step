package com.jeepark.onestep.data.model

/** 위도·경도 한 쌍. 값이 둘이 함께 바뀌도록(반쪽만 갱신된 상태가 없도록) 하나의 불변 값으로 다룬다. */
data class Coordinates(val lat: Double, val lng: Double)
