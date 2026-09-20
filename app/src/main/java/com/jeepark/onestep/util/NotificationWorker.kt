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
        if (lastAccess == 0L) {
            // 메인 화면에 도달한 적 없는 사용자에게는 안부를 보내지 않는다
            NotificationHelper.cancel(context)
            return Result.success()
        }

        val daysSince = TimeUnit.MILLISECONDS
            .toDays(System.currentTimeMillis() - lastAccess).toInt()
        val lastSentMark = prefs.getInt(NotificationHelper.KEY_LAST_CHECKIN_MARK, 0)

        val mark = CheckInPolicy.latestDueMark(daysSince, lastSentMark)
            ?: return Result.success()
        val message = CheckInPolicy.MESSAGES[mark] ?: return Result.success()

        sendNotification(message)
        prefs.edit().putInt(NotificationHelper.KEY_LAST_CHECKIN_MARK, mark).apply()

        if (mark == CheckInPolicy.FINAL_MARK) NotificationHelper.cancel(context)
        return Result.success()
    }

    private fun sendNotification(message: CheckInPolicy.Message) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, NotificationHelper.CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(message.title)
            .setContentText(message.content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        context.getSystemService(NotificationManager::class.java)
            .notify(1001, notification)
    }
}
