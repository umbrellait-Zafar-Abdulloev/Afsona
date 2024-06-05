package com.kangaroo.afsona.presentation.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kangaroo.afsona.domain.Screen
import com.kangaroo.afsona.presentation.components.StoryList
import com.kangaroo.afsona.presentation.viewModel.FavoritesViewModel

@Composable
fun FavouritesScreen(
    navController: NavController,
    onReadyToShowAd: () -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
) {

    StoryList(lazyStories = viewModel.favouriteStories){ id ->
        onReadyToShowAd()
        navController.navigate(Screen.Content.route + "/$id")
    }

}