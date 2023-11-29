package com.example.myapplication.data

import android.content.Context
import androidx.room.Query
import androidx.room.util.query
import com.example.myapplication.data.database.QuoteDatabase
import com.example.myapplication.data.database.entities.QuoteEntity
import com.example.myapplication.data.model.QuoteModel
import com.example.myapplication.data.network.QuoteService
import com.example.myapplication.domain.model.Quote
import com.example.myapplication.domain.model.toDomain


class QuoteRepository(private val context: Context) {

    private val quoteDatabase = QuoteDatabase.getDatabase(context)
    private val api = QuoteService()
    private val quoteDao = quoteDatabase.GetQuoteDao()

    suspend fun getAllQuotesFromApi(): List<Quote> {
        val response: List<QuoteModel> = api.getQuotes(String())
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase():List<Quote>{
        val response: List<QuoteEntity> = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes:List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }


}

