package com.allam.kotlinnews.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.allam.kotlinnews.model.ChildrenData
import com.allam.kotlinnews.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    /*
    * Get all new from db
    * */
    @Query("SELECT * FROM ChildrenData")
    fun getAllNews(): Flow<List<ChildrenData>>

    /*
    * insert all news
    * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<ChildrenData>)

    /*
    * insert single new
    * */
    @Insert
    suspend fun insert(childrenData: ChildrenData)

    /*
    * Get single new
    * */
    @Query("SELECT * FROM ChildrenData WHERE ID = :articleId")
    fun getArticleById(articleId: String): Flow<ChildrenData>
}