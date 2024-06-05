package com.kangaroo.afsona.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.kangaroo.afsona.App.Companion.INTERSTIAL_PLACEMENT_ID
import com.kangaroo.afsona.App.Companion.testMode
import com.kangaroo.afsona.App.Companion.unityGameID
import com.kangaroo.afsona.common.di.LoggingUtil
import com.kangaroo.afsona.domain.getLoadListener
import com.kangaroo.afsona.domain.showListener
import com.kangaroo.afsona.presentation.theme.ui.AfsonaTheme
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.UnityAds
import com.unity3d.ads.UnityAdsShowOptions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity(), IUnityAdsInitializationListener {

    private val isInterstialLoaded = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UnityAds.initialize(applicationContext, unityGameID, testMode, this)

        setContent {
            AfsonaTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation(::showAd)
                }
            }
        }
    }

    private fun showAd() = LoggingUtil.runCatching {
        if (isInterstialLoaded.value) {
            UnityAds.show(
                this@MainActivity,
                INTERSTIAL_PLACEMENT_ID,
                UnityAdsShowOptions(),
                showListener
            )

            isInterstialLoaded.value = false
            loadInterstial()
        }
    }

    override fun onInitializationComplete() {
        loadInterstial()

        FirebaseAnalytics.getInstance(this)
            .logEvent("Unity_Ad_InitializationComplete", Bundle.EMPTY)
    }

    private fun loadInterstial() = LoggingUtil.runCatching {
        UnityAds.load(INTERSTIAL_PLACEMENT_ID, getLoadListener(this, isInterstialLoaded))
    }

    override fun onInitializationFailed(p0: UnityAds.UnityAdsInitializationError?, p1: String?) {
        FirebaseAnalytics.getInstance(this)
            .logEvent("ad_initialization_error_${p0?.name}_${p1}", Bundle.EMPTY)

        FirebaseCrashlytics.getInstance()
            .recordException(Throwable(message = p0?.name + " " + p1))
    }
}