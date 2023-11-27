package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class QuoteModel(
    @SerializedName("status") val status: String,    //era author
    @SerializedName("message") val message: List<String>   //era quote

)


