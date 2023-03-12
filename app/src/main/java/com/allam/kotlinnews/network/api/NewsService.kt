package com.allam.kotlinnews.network.api

import com.allam.kotlinnews.model.Children
import com.allam.kotlinnews.model.ChildrenData
import com.allam.kotlinnews.model.News
import com.allam.kotlinnews.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {

    @GET("r/kotlin/.json")
    suspend fun getNews(): Response<NewsResponse>


    @GET("r/kotlin/.json/{newsId}")
    suspend fun getSingleArticle(
        @Path("newsId") id: String,
    ): Response<Children>




}