package com.kangaroo.afsona.common.di

import com.google.firebase.crashlytics.FirebaseCrashlytics

object LoggingUtil {

    private val crashlytics = FirebaseCrashlytics.getInstance()

    fun runCatching(block: () -> Unit) {
        kotlin.runCatching(block)
            .getOrElse(crashlytics::recordException)
    }

}