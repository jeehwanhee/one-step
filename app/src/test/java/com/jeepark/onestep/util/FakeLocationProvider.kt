package com.jeepark.onestep.util

import com.jeepark.onestep.data.model.Coordinates

/** 테스트용 가짜 LocationProvider. [current]를 마음대로 정하고 [refresh] 호출 횟수를 센다. */
class FakeLocationProvider(override var current: Coordinates? = null) : LocationProvider {

    var refreshCount = 0
        private set

    override fun refresh() {
        refreshCount++
    }
}
