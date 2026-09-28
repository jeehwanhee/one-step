package com.jeepark.onestep.domain.rules

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
}
