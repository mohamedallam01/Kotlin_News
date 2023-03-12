package com.allam.kotlinnews.repository

import com.allam.kotlinnews.model.Children
import com.allam.kotlinnews.model.ChildrenData
import com.allam.kotlinnews.model.News
import com.allam.kotlinnews.network.Resource
import kotlinx.coroutines.flow.Flow

interface DetailsRepo {

    fun getSingleArticle(articleId: String): Flow<Resource<ChildrenData>>
}