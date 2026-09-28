package com.jeepark.onestep.platform.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jeepark.onestep.MainActivity
import com.jeepark.onestep.R
import com.jeepark.onestep.appContainer

class NotificationWorker(
    private val context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        val container = context.appContainer
        // 보낼지 말지의 판단과 기록은 CheckInRunner에, 실제 알림 표시만 이 워커에 남긴다
        CheckInRunner(
            settings  = container.settingsRepository,
            scheduler = container.notificationScheduler,
            send      = ::sendNotification,
        ).run()
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
