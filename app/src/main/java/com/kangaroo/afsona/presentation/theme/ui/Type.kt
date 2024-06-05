package com.kangaroo.afsona.presentation.theme.ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kangaroo.afsona.R

val Amatica = Font(R.font.amatica_sc_bold, weight = FontWeight.Bold)
val Palatino = FontFamily(
    Font(R.font.p, weight = FontWeight.Normal),
    Font(R.font.p_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(R.font.p_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
)
val BrandFont = FontFamily(Amatica)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Palatino,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        letterSpacing = 1.sp,
        lineHeight = 20.sp
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Italic,
        fontSize = 18.sp,
        letterSpacing = 0.15.sp,
        fontFamily = Palatino
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
