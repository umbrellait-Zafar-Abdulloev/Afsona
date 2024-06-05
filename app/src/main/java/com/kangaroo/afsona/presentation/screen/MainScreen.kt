@file:OptIn(ExperimentalAnimationApi::class)

package com.kangaroo.afsona.presentation.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kangaroo.afsona.App
import com.kangaroo.afsona.domain.Screen
import com.kangaroo.afsona.presentation.MainActivity
import com.kangaroo.afsona.presentation.components.StoryList
import com.kangaroo.afsona.presentation.viewModel.MainViewModel
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBannerSize

@Composable
fun MainScreen(
    navController: NavController,
    isToolbarVisibleState: MutableState<Boolean>,
    onReadyToShowAD: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        isToolbarVisibleState.value = true
    }

    StoryList(
        header = {
                AndroidView(
                    modifier = Modifier.fillMaxWidth(),
                    factory = { mContext ->
                        BannerView(
                            (context as MainActivity),
                            App.MAIN_SCREEN_BANNER_ID,
                            UnityBannerSize.getDynamicSize(mContext)
                        ).apply(BannerView::load)
                    })
        },
        lazyStories = viewModel.stories,
        onClick = { id ->
            onReadyToShowAD()
            navController.navigate(Screen.Content.route + "/$id")
        }
    )

}