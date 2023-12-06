package com.example.myapplication.data.network

import android.util.Log
import com.example.myapplication.core.RetrofitHelper
import com.example.myapplication.data.modelApi.ListaRazasModel
import com.example.myapplication.data.modelApi.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class Service {

    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getQuotes(query: String): QuoteModel? {
        return withContext(Dispatchers.IO) {
            val query2 =query.trim()
            val endpoint = "breed/$query2/images"
            val response = retrofit.create(ApiClient::class.java).getAllQuotes(endpoint)
            Log.d("estado4 ", "respuesta de service : $response")
            Log.d("estado4 ", "respuesta de service : $query")
            Log.d("estado4 ", "respuesta de service : ${response.body()}")
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
