package com.example.newsrover.feature.data.sources.remote

import com.example.newsrover.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL ="https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/"

    fun create(): Api {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(Api::class.java)
    }
}