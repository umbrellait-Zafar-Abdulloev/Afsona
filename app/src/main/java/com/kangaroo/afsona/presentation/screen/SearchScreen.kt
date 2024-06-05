package com.kangaroo.afsona.presentation.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kangaroo.afsona.R
import com.kangaroo.afsona.domain.Screen
import com.kangaroo.afsona.presentation.components.StoryList
import com.kangaroo.afsona.presentation.viewModel.SearchViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    onReadyToShowAD: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {

    var query by rememberSaveable {
        mutableStateOf("")
    }

    StoryList(
        header = {
            Spacer(modifier = Modifier.size(16.dp))

            OutlinedTextField(
                shape = RoundedCornerShape(28.dp),
                placeholder = { Text(text = stringResource(id = R.string.search_points)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = query,
                onValueChange = { newQuery ->
                    query = newQuery
                    viewModel.search(newQuery)
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Search icon"
                    )
                }
            )

            Spacer(modifier = Modifier.size(8.dp))

        },
        stories = viewModel.foundStories.value,
        onClick = { id ->
            onReadyToShowAD()
            navController.navigate(Screen.Content.route + "/$id")
        }
    )

}