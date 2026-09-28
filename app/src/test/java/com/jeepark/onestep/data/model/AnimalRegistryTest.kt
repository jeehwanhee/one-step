package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AnimalRegistryTest {

    private val all = AnimalRegistry.all

    // ===== 식별자·이름 =====

    @Test
    fun `동물의 id와 이름은 기존 순서 그대로다`() {
        assertEquals(
            listOf("chick", "turtle", "cat", "dog", "bluebird", "bear", "horse", "dolphin"),
            all.map { it.id },
        )
        assertEquals(
            listOf("병아리", "거북이", "고양이", "강아지", "파랑새", "곰", "말", "돌고래"),
            all.map { it.name },
        )
    }

    // ===== 해금 =====

    @Test
    fun `티어가 오를 때마다 새 동물이 하나 이상 해금된다`() {
        // 티어업 안내("새 친구가 나타났어요!")가 항상 사실이려면 필요한 조건
        for (tier in 1..MAX_TIER) {
            assertTrue(
                "tier=$tier",
                AnimalRegistry.unlockedAt(tier).size > AnimalRegistry.unlockedAt(tier - 1).size,
            )
        }
    }

    // ===== 스프라이트 =====

    @Test
    fun `스프라이트는 비어 있지 않고 모든 행의 열 수가 같다`() {
        all.forEach { animal ->
            val sprite = animal.sprite
            assertTrue("${animal.id} 높이", sprite.height > 0)
            assertTrue("${animal.id} 너비", sprite.width > 0)
            sprite.pixels.forEachIndexed { row, cols ->
                assertEquals("${animal.id} ${row}행 열 수", sprite.width, cols.size)
            }
        }
    }

    @Test
    fun `스프라이트가 쓰는 색상 번호는 팔레트 안에 있다`() {
        all.forEach { animal ->
            val sprite = animal.sprite
            sprite.pixels.forEachIndexed { row, cols ->
                cols.forEachIndexed { col, colorIndex ->
                    assertTrue(
                        "${animal.id} ($row,$col)의 색상 번호 $colorIndex, 팔레트 크기 ${sprite.colors.size}",
                        colorIndex in 0 until sprite.colors.size,
                    )
                }
            }
        }
    }
}
