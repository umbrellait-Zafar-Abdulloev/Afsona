package com.kangaroo.afsona.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kangaroo.afsona.data.db.StoryDao
import com.kangaroo.afsona.domain.STORY_ID
import com.kangaroo.afsona.domain.model.Story
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val dao: StoryDao,
    stateHandle: SavedStateHandle
) : ViewModel() {

    val currentStory = stateHandle.get<String>(STORY_ID)?.let {
        dao.getById(it.toInt())
    } ?: MutableLiveData(null)


    fun changeFavStatus(story: Story) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.setFav(story.id, !story.isFav)
        }
    }

}