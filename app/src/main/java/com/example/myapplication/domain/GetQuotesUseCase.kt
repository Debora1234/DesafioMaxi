package com.example.myapplication.domain

import android.content.Context
import com.example.myapplication.data.QuoteRepository
import com.example.myapplication.data.database.entities.toDatabase
import com.example.myapplication.domain.model.Quote


//Este sería el caso de uso más básico, el cual solo llama al repositorio
// para decirle que recupere de internet todas las citas.
class GetQuotesUseCase(private val context: Context) {

    private val repository = QuoteRepository(context)
    suspend operator fun invoke(query: String): List<Quote> {
        val quotes = repository.getAllQuotesFromApi()
        if(quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
        }
        else{
            repository.getAllQuotesFromDatabase()
        }
        return quotes
    }
}
//devolvemos una lista de quotemodel,

