package com.rulhouse.redditrestfulapi.model.remote.reddit.dto

import com.google.gson.annotations.SerializedName

data class GetFirstPostsRequest(
    @SerializedName("limit") val limit: Int
)
