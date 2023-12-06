package com.example.myapplication.domain

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.myapplication.data.Repository
import com.example.myapplication.data.Repository.Companion.quoteDatabase
import com.example.myapplication.data.network.NetworkUtils

import com.example.myapplication.domain.model.Quote
import com.example.myapplication.domain.model.Raza


class GetQuotesUseCase() {

    private val repository = Repository()
    //Nuestro caso de uso la primera vez recupera las citas del servidor y las guarda en la db

    suspend operator fun invoke(query: String, context: Context): List<Quote> {

       var ultimaRazaEncontrada: String? = null

        //verificamos si hay internet
        val networkUtils = NetworkUtils(context)
         if (networkUtils.isNetworkAvailable()) {
            val quotes: List<Quote> = repository.getAllQuotesFromApi(query)
            return if (!quotes.isEmpty()) {        //si encontro algo lo insertamos en la db
                repository.clearQuotes()
                repository.insertQuotes(context, quotes)
                return quotes
            } else {                           //si no encontro nada le mostramos lo q tenemos guardado en memoria
                if(ultimaRazaEncontrada != query){
                    val quotes = repository.getAllQuotesFromDatabase(query)
                }
                return quotes
            }

        } else {
            Log.d("Network", "No hay conexión a Internet.")
             val quotes = repository.getAllQuotesFromDatabase(query)
             if(quotes.isEmpty()){
                Toast.makeText(context, "No hay conexión a Internet, y su raza $query, " +
                    "no esta cargada en su base de datos local", Toast.LENGTH_SHORT).show()
             }
             return quotes
        }
    }
}
//devolvemos una lista de quotemodel,

