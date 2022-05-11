package com.example.news.models

import com.google.gson.annotations.SerializedName

data class New(
    var title: String,
    var url: String,
    @SerializedName("published_date")
    var fecha: String,
    var media: List<Media>)
