package com.example.myapplication.data.network

import android.util.Log
import com.example.myapplication.data.modelApi.ListaRazasModel
import com.example.myapplication.data.modelApi.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class Service (private val retrofit : Retrofit) {

     suspend fun getQuotes(query: String): QuoteModel? {
        return withContext(Dispatchers.IO) {
            val query2 =query.trim()
            val endpoint = "breed/$query2/images"
            val response = retrofit.create(ApiClient::class.java).getAllQuotes(endpoint)
            response.body()
        }
    }
    suspend fun getRazas(): ListaRazasModel? {
        return withContext(Dispatchers.IO) {
            val response2 = retrofit.create(ApiClient::class.java).getAllRazas("breeds/list/all")
            Log.d("estado ", "respuesta de service : ${response2.body()}")
            response2.body()

        }
    }
}
