package com.example.myapplication.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData

import com.example.myapplication.data.database.QuoteDatabase
import com.example.myapplication.data.database.dao.QuoteDao
import com.example.myapplication.data.database.entities.QuoteEntity
import com.example.myapplication.data.database.entities.toDatabase
import com.example.myapplication.data.model.QuoteModel
import com.example.myapplication.data.network.QuoteService
import com.example.myapplication.domain.model.Quote
import com.example.myapplication.domain.model.toDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlin.text.Typography.quote


class QuoteRepository(){

    companion object {
        private var service = QuoteService()
        private lateinit var quoteDao : QuoteDao

        var quoteDatabase: QuoteDatabase? = null

        // Método de inicialización para el DAO
        fun initDB(context: Context):QuoteDatabase {
            quoteDao = QuoteDatabase.getInstance(context).getQuoteDao()
            return  QuoteDatabase.getInstance(context)
        }

    }

    //la primera vez que entro a la app, obtenemos las
    // quotes de la api y las guardamos en la base de datos
    suspend fun getAllQuotesFromApi(query : String): List<Quote> {
        Log.d("estado ", "llamamos a la api")
        val response : QuoteModel? = service.getQuotes(query)
        Log.d("estado ", "respuesta: $response")
        var perros: List<Quote>? = null
        response?.message?.forEach { mensaje ->
            val nuevaQuote = Quote(response.status, query, mensaje)
            perros = perros.orEmpty().plus(nuevaQuote)
        }
        return perros.orEmpty()
    }

    // Inserta citas en la base de datos local
    suspend fun insertQuotes(context: Context, quotes: List<Quote>) {
            quoteDatabase = initDB(context)
            CoroutineScope(IO).launch {
                quotes.forEach { quote ->
                    quoteDatabase!!.getQuoteDao().insert(quote.toDatabase())
                }

            }
    }

   // Obtiene todas las citas de la base de datos local
    suspend fun getAllQuotesFromDatabase(raza: String): List<Quote> {
       val response: List<QuoteEntity> = quoteDao.getAllQuotes(raza)
       return response.map { it.toDomain() }

    }



}


