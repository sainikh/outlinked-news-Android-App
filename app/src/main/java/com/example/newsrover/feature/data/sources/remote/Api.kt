package com.example.newsrover.feature.data.sources.remote

import com.example.newsrover.feature.data.sources.models.ResponseNews
import retrofit2.http.GET

interface Api {

    @GET("staticResponse.json")
    suspend fun fetchNews(): ResponseNews
}