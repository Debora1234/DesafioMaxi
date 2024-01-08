package com.example.myapplication.data

import android.content.Context
import android.util.Log

import com.example.myapplication.data.database.QuoteDatabase
import com.example.myapplication.data.database.dao.RazasDao
import com.example.myapplication.data.database.dao.QuoteDao
import com.example.myapplication.data.database.entities.*
import com.example.myapplication.data.modelApi.ListaRazasModel
import com.example.myapplication.data.modelApi.QuoteModel
import com.example.myapplication.data.network.Service
import com.example.myapplication.domain.model.Quote
import com.example.myapplication.domain.model.Raza
import com.example.myapplication.domain.model.toDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import com.example.myapplication.domain.model.toDomain2

import kotlinx.coroutines.launch


class Repository(private val service: Service) {

    companion object {
      //  private var service = Service()
        private lateinit var quoteDao : QuoteDao
        private lateinit var razasDao : RazasDao

        var quoteDatabase: QuoteDatabase? = null
        fun initDB(context: Context):QuoteDatabase {
            quoteDao = QuoteDatabase.getInstance(context).getQuoteDao()
            razasDao = QuoteDatabase.getInstance(context).getRazasDao()
            return  QuoteDatabase.getInstance(context)
        }
    }

    /// RAZAS***********************************************************
    suspend fun getAllListaRazasApi(): List<Raza> {
        val response: ListaRazasModel? = service.getRazas()
        Log.d("estado5", "respuesta de service de todas las razas : ${response}")
        return response?.toDomain2().orEmpty()
    }

    suspend fun insertRazas(context: Context, razas: List<Raza>) {
        if( quoteDatabase == null) quoteDatabase = initDB(context)
        CoroutineScope(IO).launch {
            razas.forEach { raza ->
                razasDao.insertRaza(raza.toDataBase())
            }
        }
    }
    suspend fun getAllRazas(): List<Raza> {
        val response: List<RazasEntity> = razasDao.getAllRazas()
        return response.map { it.toDomain() }
    }

    suspend fun clearRazas() {
        CoroutineScope(IO).launch {
            razasDao.deleteAllRazas()
        }
    }

    /// IMGANES********************************************************
    //la primera vez que entro a la app, obtenemos las
    // quotes de la api y las guardamos en la base de datos
    suspend fun getAllQuotesFromApi(query : String): List<Quote> {
        val response : QuoteModel? = service.getQuotes(query)
        var perros: List<Quote>? = null
        response?.message?.forEach { mensaje ->
            val nuevaQuote = Quote(response.status, query, mensaje)
            perros = perros.orEmpty().plus(nuevaQuote)
        }
        return perros.orEmpty()
    }

    // Inserta citas en la base de datos local
    suspend fun insertQuotes(context: Context, quotes: List<Quote>) {
        if( quoteDatabase == null) quoteDatabase = initDB(context)
        CoroutineScope(IO).launch {
             quotes.forEach { quote ->
                 quoteDao.insert(quote.toDatabase())
                        }
        }
    }

    suspend fun clearQuotes() {
        CoroutineScope(IO).launch {
            quoteDao.deleteAllQuotes()
        }
    }

   // Obtiene todas las citas de la base de datos local
    suspend fun getAllQuotesFromDatabase(raza: String): List<Quote> {
       val response: List<QuoteEntity> = quoteDao.getAllQuotes(raza)
       return response.map { it.toDomain() }
    }

}


