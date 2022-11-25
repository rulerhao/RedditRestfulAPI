package com.rulhouse.redditrestfulapi.model.remote.reddit.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Post(
    @PrimaryKey val id: String,
    val thumbnailUri: String,
    val title: String,
    val thumbnail_width: Int,
    val thumbnail_height: Int,
) : Parcelable {
    constructor() : this(
        id = "",
        thumbnailUri = "",
        title = "",
        thumbnail_width = 0,
        thumbnail_height = 0,
    )
}
