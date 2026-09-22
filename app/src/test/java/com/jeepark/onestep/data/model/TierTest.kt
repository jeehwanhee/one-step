package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TierTest {

    @Test
    fun `경험치가 다음 티어 문턱에 못 미치면 티어는 그대로다`() {
        // Arrange: tier 0의 문턱은 EXPAMOUNT[0] = 15
        // Act
        val result = calculateTierProgress(progress = 0, tier = 0, exp = 10)
        // Assert
        assertEquals(10, result.newProgress)
        assertEquals(0, result.newTier)
        assertFalse(result.didTierUp)
    }

    @Test
    fun `경험치가 정확히 문턱에 도달하면 티어가 오르고 진행도는 0이 된다`() {
        // Arrange
        // Act
        val result = calculateTierProgress(progress = 0, tier = 0, exp = 15)
        // Assert
        assertEquals(0, result.newProgress)
        assertEquals(1, result.newTier)
        assertTrue(result.didTierUp)
    }

    @Test
    fun `기존 진행도와 합쳐 문턱을 넘어도 티어가 오른다`() {
        // Arrange: tier 2의 문턱은 EXPAMOUNT[2] = 120
        // Act
        val result = calculateTierProgress(progress = 100, tier = 2, exp = 20)
        // Assert
        assertEquals(0, result.newProgress)
        assertEquals(3, result.newTier)
        assertTrue(result.didTierUp)
    }

    @Test
    fun `초과 경험치로 여러 단계를 한 번에 오를 수 있다`() {
        // Arrange: tier 0(문턱 15) -> tier 1(문턱 48)을 한 번에 넘는 경험치
        // Act
        val result = calculateTierProgress(progress = 0, tier = 0, exp = 15 + 48 + 5)
        // Assert
        assertEquals(5, result.newProgress)
        assertEquals(2, result.newTier)
        assertTrue(result.didTierUp)
    }

    @Test
    fun `최고 티어에서는 더 오르지 않고 진행도만 누적된다`() {
        // Arrange: EXPAMOUNT.size == 7 이므로 tier 7이 최고 티어
        // Act
        val result = calculateTierProgress(progress = 100, tier = EXPAMOUNT.size, exp = 50)
        // Assert
        assertEquals(150, result.newProgress)
        assertEquals(EXPAMOUNT.size, result.newTier)
        assertFalse(result.didTierUp)
    }

    @Test
    fun `경험치 0이면 아무것도 바뀌지 않는다`() {
        // Arrange
        // Act
        val result = calculateTierProgress(progress = 50, tier = 3, exp = 0)
        // Assert
        assertEquals(50, result.newProgress)
        assertEquals(3, result.newTier)
        assertFalse(result.didTierUp)
    }
}
