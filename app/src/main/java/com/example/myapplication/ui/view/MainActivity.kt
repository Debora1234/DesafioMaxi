package com.example.myapplication.ui.view

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.database.entities.QuoteEntity
import com.example.myapplication.data.network.NetworkReceiver
import com.example.myapplication.data.network.NetworkUtils

import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.model.Quote
import com.example.myapplication.domain.model.Raza
import com.example.myapplication.ui.RecycleView.Imagenes.QuoteAdapter
import com.example.myapplication.ui.RecycleView.ListadoRazas.ListadoRazasAdapter
import com.example.myapplication.ui.RecycleView.ListadoRazas.ListadoRazasViewHolder
import com.example.myapplication.ui.viewModel.QuoteViewModel
import java.util.*
import kotlin.text.Typography.quote

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel: QuoteViewModel by viewModels()
    lateinit var context: Context

 //   private lateinit var adapterImagenes: QuoteAdapter
 //   private val dogImages = mutableListOf<Quote>()

    private lateinit var adapterRazas: ListadoRazasAdapter
    private val razasLista = mutableListOf<Raza>()



    private val networkReceiver = NetworkReceiver {
        // Realiza la acción que necesitas cuando la conexión está disponible
        initRecyclerView()
        observeViewModel()
        quoteViewModel.listadoRazasOnCreate(context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSpash = installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        screenSpash.setKeepOnScreenCondition { false }

        context = this@MainActivity

        setContentView(binding.root)


        // Registra el receptor
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkReceiver, filter)

        // Comprueba la conexión cuando se inicia la actividad
        checkNetworkStatus()
        }

        private fun checkNetworkStatus() {
            val networkUtils = NetworkUtils(context)
            if (!networkUtils.isNetworkAvailable()) {
                Toast.makeText(context, "No hay conexión a Internet. Esperando...", Toast.LENGTH_LONG).show()
            }
        }

        override fun onDestroy() {
            super.onDestroy()
            // Desregistra el receptor para evitar pérdidas de memoria
            unregisterReceiver(networkReceiver)
        }

    private fun initRecyclerView() {
        //recyclerview para las imagenes de perros
      //  adapterImagenes= QuoteAdapter(dogImages)
     //   binding.rvDogs.layoutManager = LinearLayoutManager(this)
     //   binding.rvDogs.adapter =  adapterImagenes


        //recyclerview para la lista de perros
        adapterRazas = ListadoRazasAdapter(razasLista)
        binding.rvListaOpciones.layoutManager = LinearLayoutManager(this)
        binding.rvListaOpciones.adapter = adapterRazas
    }


    private fun observeViewModel() {

        quoteViewModel.lidstadoDeRazasLiveData.observe( this) { respuestaRazas ->
            Log.d("estados", " lidstadoDeRazasLiveData")
            //razasLista.clear()
            if (respuestaRazas != null) {
                razasLista.addAll(respuestaRazas)
            }
            adapterRazas.listadoRazasBaseDatosLocal = razasLista
            Log.d("estados", "respuesta entro a livedelistado $razasLista")
            adapterRazas.notifyDataSetChanged()
        }

        quoteViewModel.isLoading.observe(this) {
            binding.loading.isVisible = it
        }

        quoteViewModel.errorLiveData.observe(this) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
//boton


/*
    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

   override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            quoteViewModel.onCreateImagenes(query.toLowerCase(),  context)
            hideKeyBoard()
        }
      //  intent.putExtra(query, query)
        return true
    }

    private fun hideKeyBoard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
 */





