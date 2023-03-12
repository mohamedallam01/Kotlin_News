package com.allam.kotlinnews.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @ColumnInfo(name = "kind")
    @SerializedName("kind") var kind: String? = null,
    @ColumnInfo(name = "data")
    @SerializedName("data") var listingData: ListingData,

)
