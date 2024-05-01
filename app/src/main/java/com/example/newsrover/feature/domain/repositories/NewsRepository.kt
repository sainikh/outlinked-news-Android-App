package com.example.newsrover.feature.domain.repositories

import com.example.newsrover.feature.data.sources.models.ResponseNews

interface NewsRepository {
     suspend fun getNews(): ResponseNews
}