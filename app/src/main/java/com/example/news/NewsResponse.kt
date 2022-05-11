package com.example.news
import com.example.news.models.New

data class NewsResponse(var status: String, var results: List<New>)
