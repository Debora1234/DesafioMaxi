package com.example.myapplication.data.network

import com.example.myapplication.data.modelApi.ListaRazasModel
import com.example.myapplication.data.modelApi.QuoteModel
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Url

interface ApiClient {
    @GET
    suspend fun getAllQuotes(@Url url: String): Response<QuoteModel>

    @GET
    suspend fun getAllRazas(@Url url: String): Response<ListaRazasModel>

}
//primero ponemos el tipo de llamada, get, luego la funcion fun.