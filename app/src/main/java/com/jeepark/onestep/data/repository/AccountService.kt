package com.jeepark.onestep.data.repository

import com.jeepark.onestep.platform.notification.NotificationScheduler

/** 계정 삭제의 결과. */
enum class DeleteResult {
    /** 사용자 데이터와 인증 계정이 모두 삭제되고 로그아웃됐다. */
    Success,

    /** 사용자 데이터는 삭제됐지만 인증 계정 삭제가 실패했다(예: 최근 로그인이 필요). 로그아웃은 됐다. */
    DataDeletedButAuthFailed,

    /** 사용자 데이터를 지우지 못했다. 아무것도 바뀌지 않았고 로그인 상태도 그대로다. */
    Failed,
}

/**
 * 로그아웃과 계정 삭제. 인증·사용자 데이터·기기 설정·알림 예약에 걸친 작업이라 한 곳에 모았다.
 * 로그인 세션이 끝나면 안부 알림 예약과 접속 기록도 함께 정리한다(다른 사람에게 알림이 가지 않도록).
 */
class AccountService(
    private val auth: AuthRepository,
    private val users: UserRepository,
    private val settings: SettingsRepository,
    private val scheduler: NotificationScheduler,
) {

    fun signOut() {
        auth.signOut()
        endSession()
    }

    /**
     * 사용자 데이터 → 인증 계정 순서로 삭제한다.
     * 데이터 삭제가 실패하면 아무것도 바꾸지 않는다. 데이터가 일단 지워진 뒤에는 인증 삭제가
     * 실패해도 로그아웃하고 세션을 정리한다(지워진 계정으로 앱을 계속 쓰는 상태를 만들지 않는다).
     */
    suspend fun deleteAccount(): DeleteResult {
        val uid = auth.currentUid ?: return DeleteResult.Failed
        if (users.deleteUser(uid).isFailure) return DeleteResult.Failed

        val authDeleted = auth.deleteAuthAccount().isSuccess
        signOut()
        return if (authDeleted) DeleteResult.Success else DeleteResult.DataDeletedButAuthFailed
    }

    private fun endSession() {
        scheduler.cancel()
        settings.clearAccessRecord()
    }
}
