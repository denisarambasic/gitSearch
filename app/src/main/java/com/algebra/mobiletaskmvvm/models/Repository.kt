package com.algebra.mobiletaskmvvm.models


data class Repository(
    val id: Long,
    val repositoryName: String,
    val authorName: String,
    val authorThumbnail: String,
    val watchers: Long,
    val forks: Long,
    val openIssues: Long
)
