package com.rulhouse.redditrestfulapi.model.remote.reddit.dto

data class Image(
    val id: String,
    val resolutions: List<Resolution>,
    val source: Source,
    val variants: Variants
)