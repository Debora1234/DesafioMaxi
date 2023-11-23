package com.example.myapplication.domain

import com.example.myapplication.data.QuoteRepository
import com.example.myapplication.data.model.QuoteModel



//Este sería el caso de uso más básico, el cual solo llama al repositorio
// para decirle que recupere de internet todas las citas.
class GetQuotesUseCase {
    private val repository = QuoteRepository()
    suspend operator fun invoke(query: String):List<QuoteModel>? = repository.getAllQuotes(query)

}

//devolvemos una lista de quotemodel,