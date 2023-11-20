package com.example.myapplication.data.network

import com.example.myapplication.core.RetrofitHelper
import com.example.myapplication.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuotes(): List<QuoteModel> {
        //llamamos a la corrutina, para sobrecargar el hilo principal
        return withContext(Dispatchers.IO) {
            //de esta forma esto se hace en un hilo secundario para no sobrecargar la interfaz de usuario
            val response = retrofit.create(QuoteApiClient::class.java).getAllQuotes()
            response.body() ?: emptyList()
        }
    }

}