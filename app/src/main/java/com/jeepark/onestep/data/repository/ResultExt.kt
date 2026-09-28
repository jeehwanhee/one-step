package com.jeepark.onestep.data.repository

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

/**
 * [block]의 예외를 [Result.failure]로 바꾼다. `runCatching`과 달리 코루틴 취소([CancellationException])는
 * 삼키지 않고 그대로 던진다(취소를 실패로 처리하면 취소된 코루틴이 계속 진행된다).
 */
internal inline fun <T> suspendRunCatching(block: () -> T): Result<T> = try {
    Result.success(block())
} catch (e: CancellationException) {
    throw e
} catch (e: Exception) {
    Result.failure(e)
}

/** Firebase [Task]가 끝날 때까지 기다린다. 결과값은 필요 없을 때 쓴다. */
internal suspend fun Task<*>.awaitCompletion() {
    await()
}
