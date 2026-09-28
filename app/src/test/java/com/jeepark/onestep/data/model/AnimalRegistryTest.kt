package com.jeepark.onestep.data.model

import androidx.compose.ui.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AnimalRegistryTest {

    private val all = AnimalRegistry.all

    // ===== 식별자·이름 =====

    @Test
    fun `동물 id는 서로 겹치지 않는다`() {
        assertEquals(all.size, all.map { it.id }.toSet().size)
    }

    @Test
    fun `동물 이름은 서로 겹치지 않고 비어 있지 않다`() {
        assertEquals(all.size, all.map { it.name }.toSet().size)
        assertTrue(all.all { it.name.isNotBlank() })
    }

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

    @Test
    fun `AnimalIds 상수는 모두 레지스트리에 등록돼 있다`() {
        val constants = listOf(
            AnimalIds.CHICK, AnimalIds.TURTLE, AnimalIds.CAT, AnimalIds.DOG,
            AnimalIds.BLUEBIRD, AnimalIds.BEAR, AnimalIds.HORSE, AnimalIds.DOLPHIN,
        )

        assertEquals(constants, all.map { it.id })
    }

    @Test
    fun `id로 동물을 찾고 없는 id는 null이다`() {
        assertEquals("고양이", AnimalRegistry.get(AnimalIds.CAT)?.name)
        assertNull(AnimalRegistry.get("unicorn"))
        assertNull(AnimalRegistry.get(""))
    }

    // ===== 해금 =====

    @Test
    fun `처음부터 있는 동물은 티어 0의 병아리다`() {
        assertEquals(listOf(AnimalIds.CHICK), AnimalRegistry.unlockedAt(0).map { it.id })
    }

    @Test
    fun `모든 해금 티어는 0에서 최고 티어 사이다`() {
        assertTrue(all.all { it.unlockTier in 0..MAX_TIER })
    }

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

    @Test
    fun `최고 티어에서는 모든 동물이 해금된다`() {
        assertEquals(all, AnimalRegistry.unlockedAt(MAX_TIER))
        assertEquals(all, AnimalRegistry.unlockedAt(MAX_TIER + 50))
    }

    @Test
    fun `해금 목록은 그 티어까지의 동물을 레지스트리 순서로 담는다`() {
        assertEquals(
            listOf(AnimalIds.CHICK, AnimalIds.TURTLE, AnimalIds.CAT),
            AnimalRegistry.unlockedAt(2).map { it.id },
        )
        assertEquals(
            listOf(AnimalIds.CHICK, AnimalIds.TURTLE, AnimalIds.CAT, AnimalIds.DOG, AnimalIds.BLUEBIRD),
            AnimalRegistry.unlockedAt(4).map { it.id },
        )
    }

    @Test
    fun `티어가 음수면 아무것도 해금되지 않는다`() {
        assertTrue(AnimalRegistry.unlockedAt(-1).isEmpty())
        assertNull(AnimalRegistry.latestUnlocked(-1))
    }

    @Test
    fun `가장 최근 해금 동물은 해금 티어가 가장 높은 동물이다`() {
        for (tier in 0..MAX_TIER) {
            val latest = AnimalRegistry.latestUnlocked(tier)

            assertNotNull("tier=$tier", latest)
            assertEquals("tier=$tier", AnimalRegistry.unlockedAt(tier).maxOf { it.unlockTier }, latest?.unlockTier)
        }
        assertEquals(AnimalIds.CHICK, AnimalRegistry.latestUnlocked(0)?.id)
        assertEquals(AnimalIds.DOLPHIN, AnimalRegistry.latestUnlocked(MAX_TIER)?.id)
    }

    // ===== 공원 배치에 쓰이는 속성 =====

    @Test
    fun `하늘 동물은 파랑새뿐이다`() {
        assertEquals(listOf(AnimalIds.BLUEBIRD), all.filter { it.isSky }.map { it.id })
    }

    // ===== 대사·프로필 =====

    @Test
    fun `모든 동물은 대사가 있고 비어 있지 않다`() {
        all.forEach { animal ->
            assertTrue("${animal.id} 대사 없음", animal.messages.isNotEmpty())
            assertTrue("${animal.id} 빈 대사", animal.messages.all { it.isNotBlank() })
        }
    }

    @Test
    fun `모든 동물은 프로필 세 항목이 비어 있지 않다`() {
        all.forEach { animal ->
            val profile = animal.profile
            assertTrue("${animal.id} 성격", profile.personality.isNotBlank())
            assertTrue("${animal.id} 좋아하는 것", profile.likes.isNotBlank())
            assertTrue("${animal.id} 사는 곳", profile.habitat.isNotBlank())
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

    @Test
    fun `스프라이트의 0번 색은 투명이고 보이는 픽셀이 하나 이상 있다`() {
        all.forEach { animal ->
            assertEquals("${animal.id} 0번 색", Color.Transparent, animal.sprite.colors.first())
            assertTrue("${animal.id} 보이는 픽셀", animal.sprite.pixels.any { row -> row.any { it > 0 } })
        }
    }
}
