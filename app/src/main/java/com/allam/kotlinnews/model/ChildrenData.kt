package com.allam.kotlinnews.model

import android.text.Html
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class ChildrenData(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id") @NonNull val id: String,
    @ColumnInfo(name = "title")
    @SerializedName("title") var title: String? = null,
    @ColumnInfo(name = "thumbnail")
    @SerializedName("thumbnail") var thumbnail: String? = null,
    @ColumnInfo(name = "thumbnail_url")
    @SerializedName("thumbnail_url") var thumbnailUrl: String? = null,
    @ColumnInfo(name = "selftext")
    @SerializedName("selftext") var selfText: String? = null,
    @ColumnInfo(name = "selftext_html")
    @SerializedName("selftext_html") var selfTextHtml: String? = null
)


fun ChildrenData.getPrimaryThumbnailUrl(): String? {
    return if (!thumbnail.isNullOrEmpty()) thumbnail
    else thumbnailUrl
}

fun ChildrenData.getPrimarySelfText() : String?{
    return if (!selfText.isNullOrEmpty()) selfText
    else selfTextHtml
}
