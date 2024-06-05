package com.kangaroo.afsona.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kangaroo.afsona.App
import com.kangaroo.afsona.presentation.MainActivity
import com.kangaroo.afsona.presentation.components.ContentHeader
import com.kangaroo.afsona.presentation.components.StoryPart
import com.kangaroo.afsona.presentation.viewModel.ContentViewModel
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBannerSize

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentScreen(
    navController: NavController,
    id: String?,
    isToolbarVisibleState: MutableState<Boolean>,
    viewModel: ContentViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val currentStory by viewModel.currentStory.observeAsState()

    LaunchedEffect(key1 = Unit) {
        isToolbarVisibleState.value = false
    }

    DisposableEffect(key1 = Unit) {
        onDispose { isToolbarVisibleState.value = true }
    }

    currentStory?.let { story ->
        val storyParts = story.content.split("""(Расм)""")

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                stickyHeader {
                    ContentHeader(
                        story,
                        navController::navigateUp,
                        onFavClicked = { viewModel.changeFavStatus(story) }
                    )
                }

                item {
                    Spacer(modifier = Modifier.size(32.dp))
                }

                itemsIndexed(storyParts) { index, item ->
                    StoryPart(
                        id = story.id,
                        part = item,
                        number = index,
                        firstImage = story.image
                    )
                }

                item {
                    Spacer(modifier = Modifier.size(200.dp))
                }
            }

            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                factory = { mContext ->
                    BannerView(
                        (context as MainActivity),
                        App.CONTENT_SCREEN_BANNER_ID,
                        UnityBannerSize.getDynamicSize(mContext)
                    ).apply(BannerView::load)
                })
        }

    }

}