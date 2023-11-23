package com.example.myapplication.data

import com.example.myapplication.data.model.QuoteModel
import com.example.myapplication.data.model.QuoteProvider
import com.example.myapplication.data.network.QuoteService

class QuoteRepository {

    //llamamos al servidor
    private val api = QuoteService()

    suspend fun getAllQuotes(query: String):List<QuoteModel>{
        //primera liena, corrutina, llamo al backend y recupero las sitas
        val response = api.getQuotes(query)
        //guardamos en provider los datos que tomamos, le decimos q las quotes actuales son la respuesta q nos dio el servidor
        QuoteProvider.quotes = response
        return response
    }
}