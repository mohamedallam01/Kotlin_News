package com.allam.kotlinnews.ui.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.allam.kotlinnews.model.ChildrenData
import com.allam.kotlinnews.network.Resource
import com.allam.kotlinnews.repository.NewsRepo
import com.allam.kotlinnews.ui.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: NewsRepo
) : ViewModel() {
    val newsData = repository.getAllNews().map {
        State.fromResource(it)
    }
}