package com.jeepark.onestep.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jeepark.onestep.MainActivity
import com.jeepark.onestep.R
import java.util.concurrent.TimeUnit

class NotificationWorker(
    private val context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        val prefs = context.getSharedPreferences(
            NotificationHelper.PREFS_NAME, Context.MODE_PRIVATE
        )

        val notifEnabled = prefs.getBoolean(NotificationHelper.KEY_NOTIF, true)
        if (!notifEnabled) return Result.success()

        val lastAccess = prefs.getLong(NotificationHelper.KEY_LAST_ACCESS, 0L)
        val daysSince  = TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - lastAccess)

        when {
            daysSince in 1..7 -> sendNotification(daysSince.toInt())
            daysSince > 7     -> NotificationHelper.cancel(context)
        }

        return Result.success()
    }

    private fun sendNotification(day: Int) {
        // 메시지 개수 변동 대비 방어 코드
        val idx = (day - 1).coerceIn(0, NotificationHelper.MESSAGES.size - 1)
        val (title, content) = NotificationHelper.MESSAGES[idx]

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, NotificationHelper.CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        context.getSystemService(NotificationManager::class.java)
            .notify(1001, notification)
    }
}
