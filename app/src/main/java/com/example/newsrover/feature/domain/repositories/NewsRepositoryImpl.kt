package com.example.newsrover.feature.domain.repositories
import com.example.newsrover.feature.data.sources.models.ResponseNews
import com.example.newsrover.feature.data.sources.remote.RetrofitClient

class NewsRepositoryImpl(private val newsApi : RetrofitClient) : NewsRepository {

    override suspend fun getNews(): ResponseNews {
        return newsApi.create().fetchNews()
    }
}