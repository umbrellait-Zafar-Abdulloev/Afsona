package com.kangaroo.afsona.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kangaroo.afsona.domain.STORY_ID
import com.kangaroo.afsona.domain.Screen
import com.kangaroo.afsona.presentation.components.BottomAppBar
import com.kangaroo.afsona.presentation.screen.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(onReadyToShowAD: () -> Unit) {

    val navHostController = rememberNavController()
    val isToolbarVisibleState = remember {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {
            if (isToolbarVisibleState.value) {
                BottomAppBar(navController = navHostController)
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navHostController, startDestination = Screen.Splash.route
        ) {
            composable(route = Screen.Splash.route) { SplashScreen(navHostController) }
            composable(route = Screen.Main.route) {
                MainScreen(
                    navHostController,
                    isToolbarVisibleState,
                    onReadyToShowAD
                )
            }
            composable(route = Screen.Favourites.route) { FavouritesScreen(navHostController, onReadyToShowAD) }
            composable(route = Screen.Search.route) { SearchScreen(navHostController, onReadyToShowAD) }
            composable(route = Screen.Content.route + "/{$STORY_ID}") { entry ->
                ContentScreen(
                    navHostController,
                    entry.arguments?.get(STORY_ID).toString(),
                    isToolbarVisibleState
                )
            }
        }
    }
}