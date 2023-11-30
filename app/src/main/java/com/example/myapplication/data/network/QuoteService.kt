package com.example.myapplication.data.network

import android.util.Log
import com.example.myapplication.core.RetrofitHelper
import com.example.myapplication.data.model.QuoteModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class QuoteService {

    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getQuotes(query: String): QuoteModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(QuoteApiClient::class.java).getAllQuotes("$query/images")
            Log.d("estado ", "respuesta de service : $response")
            response.body()
        }
    }
}
