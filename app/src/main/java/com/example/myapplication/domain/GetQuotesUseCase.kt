package com.example.myapplication.domain

import android.content.Context
import android.util.Log
import com.example.myapplication.data.QuoteRepository

import com.example.myapplication.domain.model.Quote
import kotlin.text.Typography.quote


class GetQuotesUseCase() {

    private val repository = QuoteRepository()
    //Nuestro caso de uso la primera vez recupera las citas del servidor y las guarda en la db

    suspend operator fun invoke(query: String, context: Context): List<Quote> {
        //si hay intenret {
        val quotes: List<Quote> = repository.getAllQuotesFromApi(query)
        return if(!quotes.isEmpty()){        //si encontro algo lo insertamos en la db
            Log.d("estado", "message is not null")
            repository.insertQuotes(context, quotes)
            quotes
        }
        else{                           //si no encontro nada le mostramos lo q tenemos guardado en memoria
            Log.d("estado", "message is null")
            val quotes = repository.getAllQuotesFromDatabase(query)
            quotes
        }
        //cierro si hay internet

        //si no hay intenet
        //si encontro algo lo insertamos en la db
        //si no encontro nada le mostramos lo q tenemos guardado en memoria

        /* Log.d("estado", "message is null")
            val quotes = repository.getAllQuotesFromDatabase(query)
            quotes
        */

    }
}
//devolvemos una lista de quotemodel,

