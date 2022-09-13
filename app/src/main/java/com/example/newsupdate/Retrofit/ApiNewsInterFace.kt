package com.example.newsupdate.Retrofit

import com.example.newsupdate.Adaptor.HealthModelApiData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNewsInterFace {

    @GET("top-headlines")
    fun getgoogletopnews(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): Call<GoogleNewsApiModel>

    @GET("in.json")
    fun gethelthdata(): Call<HealthModelApiData>

    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsModelData>
}