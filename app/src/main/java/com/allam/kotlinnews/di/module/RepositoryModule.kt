package com.allam.kotlinnews.di.module

import com.allam.kotlinnews.db.NewsDao
import com.allam.kotlinnews.network.api.NewsService
import com.allam.kotlinnews.repository.DetailsRepo
import com.allam.kotlinnews.repository.DetailsRepoImpl
import com.allam.kotlinnews.repository.NewsRepo
import com.allam.kotlinnews.repository.NewsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(newsDao: NewsDao, newsService: NewsService): NewsRepo =
       NewsRepoImpl(newsDao, newsService)

    @Singleton
    @Provides
    fun provideDetailsRepository(
        newsDao: NewsDao,
        newsService: NewsService
    ): DetailsRepo =
        DetailsRepoImpl(newsDao, newsService)
}