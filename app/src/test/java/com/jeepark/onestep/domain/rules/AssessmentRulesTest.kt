package com.jeepark.onestep.domain.rules

import com.jeepark.onestep.domain.model.User
import com.jeepark.onestep.domain.model.assessmentState
import org.junit.Assert.assertTrue
import org.junit.Test

class AssessmentRulesTest {

    @Test
    fun `한 번도 설문하지 않았으면 설문이 필요하다`() {
        assertTrue(needsAssessment(User(isolatedHistory = emptyList()).assessmentState()))
    }
}
