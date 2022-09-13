package com.example.newsupdate.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiNewsClient {

    var BASE_URL = "https://newsapi.org/v2/"
    var static_url = "https://saurav.tech/NewsAPI/top-headlines/category/health/"

    fun getRetrofit(): Retrofit {

        var retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit
    }

    fun retrofitstatic(): Retrofit {
        var retrofit =
            Retrofit.Builder().baseUrl(static_url)
                .addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit
    }

//    https://saurav.tech/NewsAPI/top-headlines/category/health/in.json
}