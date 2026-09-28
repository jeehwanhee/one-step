package com.jeepark.onestep.domain.rules

// 회원가입 입력 규칙 (순수 함수, Android 의존 없음).

/** 가입할 수 있는 최소·최대 나이. 오타(예: 5, 999)를 걸러내는 상식적인 범위이며, 서비스 대상 연령 제한은 아니다. */
const val MIN_AGE = 10
const val MAX_AGE = 100

/** 닉네임에 쓸 수 있는 문자: 완성형 한글, 영문, 숫자. */
private val NICKNAME_PATTERN = Regex("^[가-힣a-zA-Z0-9]*$")

/** 닉네임에 쓸 수 없는 문자가 들어 있는지. 빈 닉네임은 문자 문제가 없으므로 false. */
fun hasInvalidNicknameCharacters(nickname: String): Boolean = !NICKNAME_PATTERN.matches(nickname)

/** 가입에 쓸 수 있는 닉네임인지: 비어 있지 않고 허용된 문자만 있어야 한다. */
fun isValidNickname(nickname: String): Boolean =
    nickname.isNotEmpty() && !hasInvalidNicknameCharacters(nickname)

/** 가입에 쓸 수 있는 나이인지. 입력하지 않았으면(null) 쓸 수 없다. */
fun isValidAge(age: Int?): Boolean = age != null && age in MIN_AGE..MAX_AGE
