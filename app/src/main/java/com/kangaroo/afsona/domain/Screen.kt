package com.kangaroo.afsona.domain

sealed class Screen(var route: String){
    object Splash: Screen("splash")
    object Main: Screen("main-screen")
    object Favourites : Screen("fav")
    object Search : Screen("search")
    object Content: Screen("content")
}
