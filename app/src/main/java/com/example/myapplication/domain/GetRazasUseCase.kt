package com.example.myapplication.domain

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.myapplication.data.Repository
import com.example.myapplication.data.network.NetworkUtils
import com.example.myapplication.domain.model.Quote
import com.example.myapplication.domain.model.Raza

class GetRazasUseCase() {

    private val repository = Repository()
    //Nuestro caso de uso la primera vez recupera las citas del servidor y las guarda en la db

    suspend operator fun invoke(context: Context): List<Raza> {
        //verificamos si hay internet
       val networkUtils = NetworkUtils(context)
       if (networkUtils.isNetworkAvailable()) {
            Log.d("Network", "La conexión a Internet está disponible.")
            val razas : List<Raza> = repository.getAllListaRazasApi()
            if(!razas.isEmpty()){
                Repository.Companion.initDB(context)
                repository.clearRazas()
                repository.insertRazas(context, razas)
            }
           return razas
       }
       else {
           return repository.getAllRazas()
       }

    }
}

