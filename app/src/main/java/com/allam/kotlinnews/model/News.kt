package com.allam.kotlinnews.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("kind")
    private val kind : String,
    @SerializedName("data")
    private val childrenData : List<ChildrenData>
)

