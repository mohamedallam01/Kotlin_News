package com.allam.kotlinnews.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.allam.kotlinnews.model.ChildrenData
import com.allam.kotlinnews.model.News
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [ChildrenData::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao


    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null


        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): NewsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            kotlinx.coroutines.internal.synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}