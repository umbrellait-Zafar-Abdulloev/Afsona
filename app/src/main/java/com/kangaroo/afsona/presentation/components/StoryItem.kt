package com.kangaroo.afsona.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kangaroo.afsona.domain.model.Story
import com.kangaroo.afsona.presentation.theme.ui.BlueBackground
import com.kangaroo.afsona.presentation.theme.ui.Typography

@Composable
fun StoryItemWithSpacer(item: Story, onClick: (Int) -> Unit) {

    val context = LocalContext.current
    val res = context.resources
    val cardWidth = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp / 2 - 32.dp.toPx()
    }

    Card(
        Modifier
            .clickable { onClick(item.id) }
            .width(cardWidth.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(28.dp),
        backgroundColor = BlueBackground
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val imageId = res.getIdentifier(
                item.image,
                "drawable",
                context.packageName
            )

            Image(
                painter = painterResource(id = imageId),
                contentDescription = "List item image"
            )

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                text = item.title + "\n",
                style = Typography.subtitle1
            )
        }
    }


}