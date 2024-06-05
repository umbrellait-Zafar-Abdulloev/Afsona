package com.kangaroo.afsona.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kangaroo.afsona.R
import com.kangaroo.afsona.presentation.theme.ui.BrandFont

@Composable
fun CompanyLogo() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.kangaroo),
            contentDescription = "Company logo"
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = stringResource(id = R.string.company_name),
            fontFamily = BrandFont,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 30.sp
        )
    }
}