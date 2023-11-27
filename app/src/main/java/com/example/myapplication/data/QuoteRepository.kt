package com.example.myapplication.data

import android.util.Log
import com.example.myapplication.core.RetrofitHelper
import com.example.myapplication.data.model.QuoteModel
import com.example.myapplication.data.model.QuoteProvider
import com.example.myapplication.data.network.QuoteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.GET
import retrofit2.http.Url


class QuoteRepository {
    private val api = QuoteService()

    suspend fun getAllQuotes(query: String): List<String> {
        return api.getQuotes(query)
    }
}