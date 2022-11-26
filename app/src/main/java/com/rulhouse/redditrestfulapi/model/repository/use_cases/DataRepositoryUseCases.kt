package com.rulhouse.redditrestfulapi.model.repository.use_cases

data class DataRepositoryUseCases(
    val getFirstPosts: GetFirstPosts,
    val getNextPosts: GetNextPosts,
    val getLocalPosts: GetLocalPosts,
    val insertLocalPosts: InsertLocalPosts
)
