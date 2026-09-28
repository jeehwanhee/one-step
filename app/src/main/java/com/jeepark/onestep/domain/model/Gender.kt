package com.jeepark.onestep.domain.model

/**
 * 화면·저장소 API에서 쓰는 성별. Firestore의 `users.gender`는 Boolean(true=남자)으로 저장돼 있어서
 * 저장 형식은 그대로 두고 [storedValue] / [fromStored]로 경계에서만 변환한다.
 * (`User`에 이 타입의 getter를 두면 `set(user)`가 새 필드를 만들어내므로 `User`는 Boolean을 유지한다.)
 */
enum class Gender(val storedValue: Boolean) {
    MALE(true),
    FEMALE(false);

    companion object {
        fun fromStored(value: Boolean): Gender = if (value) MALE else FEMALE
    }
}
