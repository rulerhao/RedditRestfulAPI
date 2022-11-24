package com.rulhouse.redditrestfulapi.model.remote.reddit.dto

data class Preview(
    val enabled: Boolean,
    val images: List<Image>
)