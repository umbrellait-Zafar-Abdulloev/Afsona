package com.kangaroo.afsona.domain

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import com.unity3d.ads.IUnityAdsLoadListener
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds

fun getLoadListener(context: Context, loadState: MutableState<Boolean>) =
    object : IUnityAdsLoadListener {
        override fun onUnityAdsAdLoaded(placementId: String) {
            loadState.value = true
        }

        override fun onUnityAdsFailedToLoad(
            placementId: String,
            error: UnityAds.UnityAdsLoadError,
            message: String
        ) {
            loadState.value = false

            FirebaseAnalytics.getInstance(context)
                .logEvent(
                    "UnityAdsLoadInterstialError",
                    bundleOf(
                        message to
                                "Unity Ads failed to load ad for $placementId with error: [$error] $message"
                    )
                )
        }
    }

val Activity.showListener: IUnityAdsShowListener
    get() = object : IUnityAdsShowListener {
        override fun onUnityAdsShowFailure(
            placementId: String,
            error: UnityAds.UnityAdsShowError,
            message: String
        ) {
            FirebaseAnalytics.getInstance(this@showListener)
                .logEvent(
                    "onUnityAdsShowFailure",
                    bundleOf(
                        "message" to
                                "Unity Ads failed to show ad for $placementId with error: [$error] $message"
                    )
                )
        }

        override fun onUnityAdsShowStart(placementId: String) {
            Toast.makeText(
                this@showListener,
                """Баъди реклама, Шумо метавонед истифодаи барномаро давом дихед ⏩""",
                Toast.LENGTH_SHORT
            ).show()
            Log.v("UnityAdsExample", "onUnityAdsShowStart: $placementId")
        }

        override fun onUnityAdsShowClick(placementId: String) {
            Log.v("UnityAdsExample", "onUnityAdsShowClick: $placementId")
        }

        override fun onUnityAdsShowComplete(
            placementId: String,
            state: UnityAds.UnityAdsShowCompletionState
        ) {
            Toast.makeText(
                this@showListener,
                """Ташаккур барои дастгириятон 🙏""",
                Toast.LENGTH_SHORT
            ).show()
            Log.v("UnityAdsExample", "onUnityAdsShowComplete: $placementId")
        }
    }