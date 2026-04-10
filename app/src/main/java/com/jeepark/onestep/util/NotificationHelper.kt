package com.jeepark.onestep.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

object NotificationHelper {

    const val CHANNEL_ID   = "onestep_daily"
    const val WORK_NAME    = "daily_notification"
    const val PREFS_NAME   = "app_settings"
    const val KEY_NOTIF    = "notification_enabled"
    const val KEY_LAST_ACCESS = "last_access_date"

    val MESSAGES = listOf(
        "오늘 한 걸음은요?"         to "오늘의 퀘스트가 기다리고 있어요",
        "동물들이 보고싶어해요"      to "어제보다 한 걸음만 더 나아가볼까요?",
        "병아리가 기다리고 있어요"   to "잠깐이라도 들러볼까요?",
        "퀘스트가 쌓이고 있어요"     to "작은 한 걸음이 큰 변화를 만들어요",
        "슬슬 나와볼 때가 됐어요"    to "오늘 딱 하나만 해봐요",
        "동물들이 울고 있어요"       to "오랫동안 못 봤네요, 보고 싶었어요",
        "마지막으로 한 번만요"       to "오늘 한 걸음, 내일이 달라져요"
    )

    fun createChannel(context: Context) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "일일 퀘스트 알림",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "매일 퀘스트를 독려하는 알림입니다"
        }
        context.getSystemService(NotificationManager::class.java)
            .createNotificationChannel(channel)
    }

    fun schedule(context: Context) {
        val now = Calendar.getInstance()
        val target = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 20)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (!after(now)) add(Calendar.DAY_OF_MONTH, 1)
        }
        val initialDelay = target.timeInMillis - now.timeInMillis

        val request = PeriodicWorkRequestBuilder<NotificationWorker>(24, TimeUnit.HOURS)
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )
    }

    fun cancel(context: Context) {
        WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
    }

    fun saveLastAccess(context: Context) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putLong(KEY_LAST_ACCESS, System.currentTimeMillis())
            .apply()
    }
}
