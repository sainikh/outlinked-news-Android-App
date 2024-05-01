package com.example.newsrover.feature.data.sources.models

data class NewsArticle (
    val author: String?,
    val title: String,
    val publishedAt: String,
    val source: String,
    val url: String,
    val urlToImage: String,
)