package com.kangaroo.afsona.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kangaroo.afsona.domain.model.Story
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoryList(
    header: @Composable () -> Unit = {},
    lazyStories: Flow<PagingData<Story>> = flowOf(PagingData.empty()),
    stories: List<Story> = emptyList(),
    onClick: (Int) -> Unit
) {

    val lazyLocalStories = lazyStories.collectAsLazyPagingItems()

    Column(Modifier.fillMaxSize()) {
        header()

        LazyVerticalGrid(modifier = Modifier.fillMaxSize(), cells = GridCells.Fixed(2)) {

            items(lazyLocalStories) { item ->
                StoryItemWithSpacer(item, onClick)
            }

            items(stories) { item ->
                StoryItemWithSpacer(item, onClick)
            }
        }
    }

}

@Composable
private fun StoryItemWithSpacer(
    item: Story?,
    onClick: (Int) -> Unit
) {
    item?.let {
        StoryItemWithSpacer(
            item = it,
            onClick = onClick
        )

        Spacer(modifier = Modifier.size(16.dp))
    }
}

@ExperimentalFoundationApi
fun <T : Any> LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems[index])
    }
}