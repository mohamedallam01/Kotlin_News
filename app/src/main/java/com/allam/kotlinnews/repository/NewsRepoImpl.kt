package com.allam.kotlinnews.repository

import android.util.Log
import com.allam.kotlinnews.db.NewsDao
import com.allam.kotlinnews.model.Children
import com.allam.kotlinnews.model.ChildrenData
import com.allam.kotlinnews.model.News
import com.allam.kotlinnews.model.NewsResponse
import com.allam.kotlinnews.network.NetworkBoundRepository
import com.allam.kotlinnews.network.Resource
import com.allam.kotlinnews.network.api.NewsService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
class NewsRepoImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val newsService: NewsService
) : NewsRepo {

    override fun getAllNews(): Flow<Resource<List<ChildrenData>>> {
        return object : NetworkBoundRepository<List<ChildrenData>, NewsResponse>() {
            override fun fetchFromLocal(): Flow<List<ChildrenData>> =
                newsDao.getAllNews()

            override suspend fun fetchFromRemote(): Response<NewsResponse> {
                return newsService.getNews()

            }

            override suspend fun saveRemoteData(response: NewsResponse) {
                //Convert children to childrenData
                val childrenData = arrayListOf<ChildrenData>()
                response.listingData.children.forEach {
                    childrenData.add(it.childrenData)
                }
                newsDao.insertAll(childrenData)
            }
        }.asFlow()
    }


}