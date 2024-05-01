package com.example.newsrover.feature.data.sources.models

data class ResponseNews(
    val articles: List<Article>,
    val status: String
)