package com.allam.kotlinnews.repository

import com.allam.kotlinnews.db.NewsDao
import com.allam.kotlinnews.model.Children
import com.allam.kotlinnews.model.ChildrenData
import com.allam.kotlinnews.model.News
import com.allam.kotlinnews.network.NetworkBoundRepository
import com.allam.kotlinnews.network.Resource
import com.allam.kotlinnews.network.api.NewsService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject



@OptIn(ExperimentalCoroutinesApi::class)
class DetailsRepoImpl@Inject constructor(
    private val newsDao: NewsDao,
    private val newsService: NewsService
) : DetailsRepo {

    override fun getSingleArticle(articleId: String): Flow<Resource<ChildrenData>> {
        return object : NetworkBoundRepository<ChildrenData, Children>() {
            override suspend fun saveRemoteData(response: Children) {
                newsDao.insert(childrenData = response.childrenData)
            }

            override fun fetchFromLocal(): Flow<ChildrenData> {
                return newsDao.getArticleById(articleId)
            }

            override suspend fun fetchFromRemote(): Response<Children> {
                return newsService.getSingleArticle(articleId)
            }


        }.asFlow()


    }

}