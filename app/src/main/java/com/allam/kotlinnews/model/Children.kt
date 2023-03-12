package com.allam.kotlinnews.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("kind")
    val kind : String,
    @SerializedName("data")
    val childrenData : ChildrenData
)
