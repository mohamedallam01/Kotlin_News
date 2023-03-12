package com.allam.kotlinnews.model

import com.google.gson.annotations.SerializedName

data class ListingData(
    @SerializedName("children")
     val children : List<Children>
)
