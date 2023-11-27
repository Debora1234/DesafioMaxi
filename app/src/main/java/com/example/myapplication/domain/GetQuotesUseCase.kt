package com.example.myapplication.domain

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.myapplication.data.QuoteRepository
import com.example.myapplication.data.model.QuoteModel
import com.example.myapplication.data.model.QuoteProvider
import com.example.myapplication.data.model.QuoteProvider.Companion.quotes


//Este sería el caso de uso más básico, el cual solo llama al repositorio
// para decirle que recupere de internet todas las citas.
class GetQuotesUseCase {
    private val repository = QuoteRepository()

    suspend operator fun invoke(query: String): List<String> {
        return repository.getAllQuotes(query)
    }
}
//devolvemos una lista de quotemodel,

