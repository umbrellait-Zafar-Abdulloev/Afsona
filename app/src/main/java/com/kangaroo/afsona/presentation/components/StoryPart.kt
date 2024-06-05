package com.kangaroo.afsona.presentation.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kangaroo.afsona.presentation.theme.ui.Typography

@Composable
fun StoryPart(id: Int, part: String, number: Int, firstImage: String) {

    val imageResId = rememberImageId(number, firstImage, id)

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        if (imageResId != null && imageResId != 0) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally),
                painter = painterResource(id = imageResId),
                contentDescription = "Content image"
            )
            Spacer(modifier = Modifier.size(16.dp))
        }

        Text(text = part, style = Typography.body1)
    }

}

@Composable
private fun rememberImageId(number: Int, firstImage: String, id: Int): Int? {
    val context = LocalContext.current
    return remember {
        runCatching {
            getResIdByString(
                context = context,
                id = if (number == 0) firstImage else "id${id}_$number"
            )
        }.getOrNull()
    }
}

private fun getResIdByString(context: Context, id: String) =
    context.resources.getIdentifier(id, "drawable", context.packageName)