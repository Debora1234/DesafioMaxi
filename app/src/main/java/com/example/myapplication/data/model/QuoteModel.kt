package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class QuoteModel(
    @SerializedName("message") val images: List<String>,   //era quote
    @SerializedName("status") val status: String    //era author
)
