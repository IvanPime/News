package com.example.news.models

import com.google.gson.annotations.SerializedName

data class Media(
        @SerializedName("media-metadata")
        var mediasMetaData: List<MediaMetaData>
    )
