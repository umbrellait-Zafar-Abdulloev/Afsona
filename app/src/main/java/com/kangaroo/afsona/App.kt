package com.kangaroo.afsona

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.kangaroo.afsona.domain.NotificationWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    companion object {
        const val CONTENT_SCREEN_BANNER_ID = "content_screen_banner"
        const val MAIN_SCREEN_BANNER_ID = "main_screen_banner"
        const val INTERSTIAL_PLACEMENT_ID = "click_item_interstial"
        const val unityGameID = "4667809"
        const val testMode = false
    }

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() = Configuration.Builder()
        .setWorkerFactory(workerFactory)
        .build()

    override fun onCreate() {
        super.onCreate()
        NotificationWorker.schedule(applicationContext, 20, 15)
    }
}