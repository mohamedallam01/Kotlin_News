package com.allam.kotlinnews.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.allam.kotlinnews.repository.DetailsRepo
import com.allam.kotlinnews.ui.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: DetailsRepo
) : ViewModel() {


    fun getMovieDetail(newsId: String) = repository.getSingleArticle(newsId).map {
        State.fromResource(it)
    }


}