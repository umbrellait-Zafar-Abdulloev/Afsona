package com.kangaroo.afsona.presentation.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.kangaroo.afsona.R
import com.kangaroo.afsona.domain.Screen
import com.kangaroo.afsona.presentation.components.CompanyLogo
import com.kangaroo.afsona.presentation.theme.ui.BlueBackground
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    val scale = remember {
        Animatable(0F)
    }

    LaunchedEffect(key1 = Unit, block = {
        scale.animateTo(
            targetValue = 1F,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(2F).getInterpolation(it)
                }
            ))

        delay(2_000)

        navController.popBackStack()
        navController.navigate(Screen.Main.route)
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BlueBackground),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.scale(scale.value),
            painter = painterResource(id = R.drawable.afsona),
            contentDescription = "App image"
        )

        CompanyLogo()
    }

}