package com.kangaroo.afsona.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.kangaroo.afsona.data.db.StoryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dao: StoryDao
) : ViewModel() {

    val stories = Pager(PagingConfig(10)) { dao.getAll() }
        .flow.cachedIn(viewModelScope)

}
