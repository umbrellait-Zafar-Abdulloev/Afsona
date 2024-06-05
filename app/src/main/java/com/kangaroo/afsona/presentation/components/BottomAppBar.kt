package com.kangaroo.afsona.presentation.components

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kangaroo.afsona.R
import com.kangaroo.afsona.domain.Screen
import com.kangaroo.afsona.presentation.theme.ui.BlueBackground
import com.kangaroo.afsona.presentation.theme.ui.WhiteTransparent


@Preview(showBackground = true)
@Composable
fun BottomAppBar_preview() {
    BottomAppBar(rememberNavController())
}

@Composable
fun BottomAppBar(navController: NavController) {
    BottomNavigation(
        modifier = Modifier.clip(
            RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ),
        backgroundColor = BlueBackground,
    ) {
        BottomNavigationItem(
            navController = navController,
            screen = Screen.Main,
            vector = Icons.Rounded.Home,
            labelId = R.string.home_label
        )

        BottomNavigationItem(
            navController = navController,
            screen = Screen.Favourites,
            vector = Icons.Rounded.Favorite,
            labelId = R.string.fav
        )

        BottomNavigationItem(
            navController = navController,
            screen = Screen.Search,
            vector = Icons.Rounded.Search,
            labelId = R.string.search
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun RowScope.BottomNavigationItem(
    navController: NavController,
    screen: Screen,
    vector: ImageVector,
    @StringRes labelId: Int
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    BottomNavigationItem(
        selected = selected, alwaysShowLabel = false,

        onClick = {
            navigateToScreen(screen, navController)
        },
        icon = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = vector,
                contentDescription = null
            )
        },
        label = {
            Text(
                text = stringResource(id = labelId),
                fontSize = 10.sp
            )
        },
        selectedContentColor = Color.White,
        unselectedContentColor = WhiteTransparent
    )
}

private fun navigateToScreen(screen: Screen, navController: NavController) {
    navController.navigate(route = screen.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}