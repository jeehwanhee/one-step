package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SignupRulesTest {

    // ===== 닉네임 =====

    @Test
    fun `한글 영문 숫자와 그 조합은 쓸 수 있다`() {
        listOf("한걸음", "walker", "Walker7", "2026", "한걸음123abc", "가", "힣").forEach { nickname ->
            assertTrue("nickname=$nickname", isValidNickname(nickname))
        }
    }

    @Test
    fun `공백 특수문자 이모지는 쓸 수 없다`() {
        listOf("한 걸음", "walker!", "닉네임_1", "a-b", "이름🙂", "a.b", "탭\t").forEach { nickname ->
            assertTrue("nickname=$nickname", hasInvalidNicknameCharacters(nickname))
            assertFalse("nickname=$nickname", isValidNickname(nickname))
        }
    }

    @Test
    fun `자음만 있거나 모음만 있는 한글 자모는 쓸 수 없다`() {
        // 완성형 글자(가-힣)만 허용하므로 ㅋㅋ, ㅏ 같은 낱자는 걸러진다
        listOf("ㅋㅋ", "ㅏ", "한ㄱ").forEach { nickname ->
            assertFalse("nickname=$nickname", isValidNickname(nickname))
        }
    }

    @Test
    fun `빈 닉네임은 문자 문제는 없지만 가입에는 쓸 수 없다`() {
        assertFalse(hasInvalidNicknameCharacters(""))
        assertFalse(isValidNickname(""))
    }

    // ===== 나이 =====

    @Test
    fun `나이 범위의 경계는 10세와 100세다`() {
        assertEquals(10, MIN_AGE)
        assertEquals(100, MAX_AGE)
    }

    @Test
    fun `범위 안의 나이는 쓸 수 있다`() {
        listOf(MIN_AGE, 19, 25, 38, MAX_AGE).forEach { age ->
            assertTrue("age=$age", isValidAge(age))
        }
    }

    @Test
    fun `범위 밖이거나 입력하지 않은 나이는 쓸 수 없다`() {
        listOf(null, 0, 1, MIN_AGE - 1, MAX_AGE + 1, 999, -3).forEach { age ->
            assertFalse("age=$age", isValidAge(age))
        }
    }
}
