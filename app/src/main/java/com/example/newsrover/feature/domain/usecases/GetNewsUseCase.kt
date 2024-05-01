package com.example.newsrover.feature.domain.usecases

import com.example.newsrover.feature.data.sources.models.NewsArticle
import com.example.newsrover.feature.domain.repositories.NewsRepositoryImpl

class GetNewsUseCase(private val newsRepository: NewsRepositoryImpl) {
    suspend fun execute(): List<NewsArticle> {
        val serverResponse = newsRepository.getNews()
        if (serverResponse.status == "ok") {
            return serverResponse.articles.map { news ->
                NewsArticle(
                    news.author,
                    news.title,
                    news.publishedAt,
                    news.source.name,
                    if (news.url.startsWith("http://")) {
                        "https://" + news.url.substring(7)
                    } else {
                        news.url
                    },
                    news.urlToImage
                )
            }
        }

        return ArrayList<NewsArticle>()
    }
}