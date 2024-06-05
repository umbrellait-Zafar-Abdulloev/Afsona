package com.kangaroo.afsona.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kangaroo.afsona.R
import com.kangaroo.afsona.domain.model.Story
import com.kangaroo.afsona.presentation.theme.ui.BlueBackground
import com.kangaroo.afsona.presentation.theme.ui.Typography

@Composable
fun ContentHeader(story: Story, onCloseClicked: () -> Unit, onFavClicked: () -> Unit) {

    val context = LocalContext.current

    Card(
        Modifier.fillMaxWidth(),
        backgroundColor = BlueBackground,
        shape = RoundedCornerShape(bottomEnd = 28.dp, bottomStart = 28.dp)
    ) {
        Box(modifier = Modifier.padding(vertical = 12.dp)) {

            Box {
                IconButton(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .align(Alignment.CenterStart), onClick = onCloseClicked
                ) {
                    Icon(imageVector = Icons.Rounded.Close, contentDescription = "Close button")
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 60.dp)
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    text = story.title,
                    style = Typography.subtitle1.copy(fontSize = 24.sp)
                )

                IconButton(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .align(Alignment.CenterEnd),
                    onClick = {
                        Toast.makeText(
                            context,
                            if (story.isFav) R.string.removed_from_fav
                            else R.string.added_to_fav,
                            Toast.LENGTH_SHORT
                        ).show()
                        onFavClicked()
                    }
                ) {
                    Icon(
                        imageVector = if (story.isFav) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                        contentDescription = "Close button"
                    )
                }
            }
        }
    }

}
