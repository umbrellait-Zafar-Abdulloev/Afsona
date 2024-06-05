package com.kangaroo.afsona.domain

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.kangaroo.afsona.R
import com.kangaroo.afsona.data.db.StoryDao
import com.kangaroo.afsona.domain.model.Story
import com.kangaroo.afsona.presentation.MainActivity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val database: StoryDao
) : Worker(appContext, workerParams) {

    companion object {

        private const val TAG_REMINDER_WORKER = "notification-worker"

        /**
         * @param hourOfDay the hour at which daily reminder notification should appear [0-23]
         * @param minute the minute at which daily reminder notification should appear [0-59]
         */
        fun schedule(appContext: Context, hourOfDay: Int, minute: Int) {
            println("Reminder scheduling request received for $hourOfDay:$minute")
            val now = Calendar.getInstance()
            val target = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hourOfDay)
                set(Calendar.MINUTE, minute)
            }

            if (target.before(now)) {
                target.add(Calendar.DAY_OF_YEAR, 1)
            }

            println("Scheduling reminder notification for ${target.timeInMillis - System.currentTimeMillis()} ms from now")

            val notificationRequest = PeriodicWorkRequestBuilder<NotificationWorker>(
                24,
                TimeUnit.HOURS
            )
                .addTag(TAG_REMINDER_WORKER)
                .setInitialDelay(
                    target.timeInMillis - System.currentTimeMillis(),
                    TimeUnit.MILLISECONDS
                ).build()

            WorkManager.getInstance(appContext)
                .enqueueUniquePeriodicWork(
                    "reminder_notification_work",
                    ExistingPeriodicWorkPolicy.REPLACE,
                    notificationRequest
                )
        }
    }

    private val CHANNEL_ID = applicationContext.getString(R.string.app_name)

    override fun doWork(): Result {
        return try {
            val date = Calendar.getInstance().time
            CoroutineScope(Dispatchers.IO).launch {
                val story = database.getByIdAsync(getDayOfMonth(date))
                withContext(Dispatchers.Main) {
                    showNotification(story)
                    Result.success()
                }
            }
            Result.success()

        } catch (e: Exception) {
            val data = Data.Builder().putString("message", e.localizedMessage).build()
            Result.failure(data)
        }
    }

    private fun showNotification(story: Story) {
        createNotificationChannel()

        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = getPendingIntent(intent)

        val builder = getNotificationBuilder(pendingIntent, story)

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(Random.nextInt(), builder.build())
        }
    }

    private fun getNotificationBuilder(
        pendingIntent: PendingIntent?,
        story: Story
    ): NotificationCompat.Builder {

        var imageId = applicationContext.resources.getIdentifier(
            story.image, "drawable", applicationContext.packageName
        )

        if (imageId == 0) {
            imageId = R.mipmap.ic_launcher
        }

        return NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(imageId)
            .setContentTitle("""Салом👋 Вакти афсона расид 🌃🌠🌆""")
            .setContentText(
                "${applicationContext.getString(R.string.notificationMessage)} ${story.title}"
            )
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }

    private fun getPendingIntent(intent: Intent) = PendingIntent.getActivity(
        applicationContext,
        0,
        intent,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
    )

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = applicationContext.getString(R.string.app_name)
            val descriptionText = applicationContext.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            getNotificationManager().createNotificationChannel(channel)
        }
    }

    private fun getNotificationManager() = getSystemService(
        applicationContext,
        NotificationManager::class.java
    ) as NotificationManager

    private fun getDayOfMonth(date: Date): Int {
        val cal = Calendar.getInstance()
        cal.time = date
        return cal[Calendar.DAY_OF_MONTH]
    }
}
