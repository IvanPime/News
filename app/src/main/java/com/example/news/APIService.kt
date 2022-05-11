package com.example.news

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getNews(@Url url:String): Response<NewsResponse>
}