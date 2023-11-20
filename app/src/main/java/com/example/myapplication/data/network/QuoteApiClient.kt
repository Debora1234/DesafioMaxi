package com.example.myapplication.data.network

import com.example.myapplication.data.model.QuoteModel
import retrofit2.http.GET
import retrofit2.Response

interface QuoteApiClient {
    @GET("/.json")
    suspend fun getAllQuotes(): Response<List<QuoteModel>>
}