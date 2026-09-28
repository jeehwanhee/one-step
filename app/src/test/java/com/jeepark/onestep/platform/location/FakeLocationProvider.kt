package com.jeepark.onestep.platform.location

import com.jeepark.onestep.domain.model.Coordinates

/** 테스트용 가짜 LocationProvider. [current]를 마음대로 정한다. */
class FakeLocationProvider(override var current: Coordinates? = null) : LocationProvider {

    override fun refresh() = Unit
}
