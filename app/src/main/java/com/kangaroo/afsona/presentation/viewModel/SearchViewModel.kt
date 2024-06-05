package com.kangaroo.afsona.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kangaroo.afsona.data.db.StoryDao
import com.kangaroo.afsona.domain.model.Story
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val dao: StoryDao) : ViewModel() {

    val foundStories = mutableStateOf(emptyList<Story>())

    fun search(query: String) {
        if (query.length <= 1) {
            foundStories.value = emptyList()
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            foundStories.value = dao.search(query = query)
        }
    }

}