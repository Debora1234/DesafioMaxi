package com.example.myapplication.data.network

import com.example.myapplication.data.model.QuoteModel
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Url

interface QuoteApiClient {
    @GET
    suspend fun getAllQuotes(@Url url: String): Response<QuoteModel>
}
//primero ponemos el tipo de llamada, get, luego la funcion fun.