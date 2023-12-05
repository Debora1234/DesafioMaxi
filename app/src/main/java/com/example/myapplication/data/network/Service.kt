package com.example.myapplication.data.network

import android.util.Log
import com.example.myapplication.core.RetrofitHelper
import com.example.myapplication.data.model.ListaRazasModel
import com.example.myapplication.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class Service {

    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getQuotes(query: String): QuoteModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getAllQuotes("breed/$query/images")
            Log.d("estado ", "respuesta de service : $response")
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

   // suspend fun getAllRazas()
}