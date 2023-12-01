package com.example.myapplication.domain

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.myapplication.data.QuoteRepository
import com.example.myapplication.data.network.NetworkUtils

import com.example.myapplication.domain.model.Quote
import kotlin.text.Typography.quote


class GetQuotesUseCase() {


    private val repository = QuoteRepository()
    //Nuestro caso de uso la primera vez recupera las citas del servidor y las guarda en la db

    suspend operator fun invoke(query: String, context: Context): List<Quote> {

        //verificamos si hay internet
        val networkUtils = NetworkUtils(context)
         if (networkUtils.isNetworkAvailable()) {
            Log.d("Network", "La conexión a Internet está disponible.")
            val quotes: List<Quote> = repository.getAllQuotesFromApi(query)
            return if (!quotes.isEmpty()) {        //si encontro algo lo insertamos en la db
                Log.d("estado", "message is not null")
                repository.insertQuotes(context, quotes)
                return quotes
            } else {                           //si no encontro nada le mostramos lo q tenemos guardado en memoria
                Log.d("estado", "message is null")
                val quotes = repository.getAllQuotesFromDatabase(query)
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

