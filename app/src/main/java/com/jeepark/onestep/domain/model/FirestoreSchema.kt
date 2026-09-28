package com.jeepark.onestep.domain.model

import com.jeepark.onestep.domain.rules.INIT_QUESTIONS

// Firestore에 이미 저장된 데이터의 위치와 필드 이름.
// 서버에 있는 사용자 데이터를 읽고 쓰기 위한 이름이라 바꾸면 기존 사용자의 데이터가 조용히 사라지거나 다르게 읽힌다.
// 값은 PersistedValuesTest가 고정하고, 모델 프로퍼티와의 일치도 그 테스트가 확인한다.

/** 컬렉션과 문서 이름. */
object FirestorePaths {
    const val USERS = "users"
    const val QUESTS = "quests"
    const val PLACES = "places"

    /** 컬렉션별 데이터 버전을 담은 문서들이 있는 컬렉션. 문서의 [META_VERSION_FIELD] 값으로 캐시를 새로 받을지 판단한다. */
    const val META = "meta"
    const val QUESTS_META_DOC = "quests_meta"
    const val PLACES_META_DOC = "places_meta"
    const val META_VERSION_FIELD = "version"
}

/**
 * `users` 문서의 필드 이름. [User]의 프로퍼티 이름과 같다(`set`/`toObject`가 이름으로 대응시킨다).
 *
 * [GIVE_UP_REASONS]는 예외다. 퀘스트를 포기할 때마다 `{questName, reason}` 맵을 배열에 추가하는 통계용 필드이고,
 * 앱은 쓰기만 하고 읽지 않아서 [User]에는 두지 않는다(모델로 읽으면 기록이 쌓일수록 메모리만 쓴다).
 * 같은 이름의 필드가 `quests` 문서에도 있지만 모양이 다르다([QuestFields.GIVE_UP_REASONS] 참고).
 */
object UserFields {
    const val INIT_QUESTIONS = "initQuestions"
    const val ISOLATED = "isolated"
    const val ISOLATED_LAST_MODIFIED = "isolatedLastModified"
    const val ISOLATED_HISTORY = "isolatedHistory"
    const val QUESTS_SINCE_ASSESSMENT = "questsSinceAssessment"
    const val PROGRESS = "progress"
    const val TIER = "tier"
    const val DIFFICULTY_QUEUE = "difficultyQueue"
    const val QUEST_RESULTS_QUEUE = "questResultsQueue"
    const val PREV_QUESTS = "prevQuests"
    const val LAST_ACCESS_DATE = "lastAccessDate"
    const val NOTIFICATION_AGREED = "notificationAgreed"
    const val DAILY_QUEST_COUNT = "dailyQuestCount"
    const val DAILY_QUEST_DATE = "dailyQuestDate"

    /** 포기 기록: `{questName, reason}` 맵([GiveUpEntryFields])의 배열. 모델에는 없다. */
    const val GIVE_UP_REASONS = "giveUpReasons"
}

/** `quests` 문서의 필드 이름. [Quest]의 프로퍼티 이름과 같다(단, [GIVE_UP_REASONS] 제외). */
object QuestFields {
    const val INDEX = "index"

    /** 이 퀘스트를 포기한 사유 코드([GiveUpReason.code])의 배열. `users`의 같은 이름 필드와 모양이 다르다. 모델에는 없다. */
    const val GIVE_UP_REASONS = "giveUpReasons"
}

/** [IsolatedRecord]의 필드 이름(`users.isolatedHistory` 배열의 원소). */
object IsolatedRecordFields {
    const val SCORE = "score"
    const val RECORDED_AT = "recordedAt"
}

/** [PrevQuest]의 필드 이름(`users.prevQuests` 배열의 원소). */
object PrevQuestFields {
    const val QUEST_NAME = "questName"
    const val QUEST_EXP = "questEXP"
    const val DIFFICULTY = "difficulty"
    const val CONFIRM_QUESTION = "confirmQuestion"
    const val CONFIRM_ANSWER = "confirmAnswer"
    const val DONE_DATE = "doneDate"
}

/** `users.giveUpReasons` 배열의 원소(맵)의 키. 모델은 없다. */
object GiveUpEntryFields {
    const val QUEST_NAME = "questName"
    const val REASON = "reason"
}
