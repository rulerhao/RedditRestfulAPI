package com.rulhouse.redditrestfulapi.model.reddit.dto

data class Preview(
    val enabled: Boolean,
    val images: List<Image>
)