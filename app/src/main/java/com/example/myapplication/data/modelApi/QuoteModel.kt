package com.example.myapplication.data.modelApi

import com.google.gson.annotations.SerializedName

data class QuoteModel(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: List<String>
)

