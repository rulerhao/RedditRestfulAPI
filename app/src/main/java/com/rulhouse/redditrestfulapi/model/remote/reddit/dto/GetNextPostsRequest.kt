package com.rulhouse.redditrestfulapi.model.remote.reddit.dto

import com.google.gson.annotations.SerializedName

data class GetNextPostsRequest(
    @SerializedName("limit") val limit: Int,
    @SerializedName("after") val after: String
)
