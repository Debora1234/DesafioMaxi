package com.example.myapplication.ui.view

import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room

import com.example.myapplication.data.database.QuoteDatabase
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.model.Quote
import com.example.myapplication.ui.RecycleView.ListadoRazasAdapter
import com.example.myapplication.ui.RecycleView.QuoteAdapter
import com.example.myapplication.ui.viewModel.QuoteViewModel

class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel: QuoteViewModel by viewModels()
    lateinit var context: Context

    private lateinit var adapter: QuoteAdapter
    private val dogImages = mutableListOf<Quote>()

    private lateinit var searchView: SearchView
    private lateinit var listadoRazasAdapter: ListadoRazasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSpash = installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        screenSpash.setKeepOnScreenCondition { false }

        context = this@MainActivity

        setContentView(binding.root)


        binding.svDogs.setOnQueryTextListener(this)
        initRecyclerView()
        Log.d("estado ", "2")

        quoteViewModel.dogImagesLiveData.observe( this) { respuesta ->
            dogImages.clear()
            if (respuesta != null) {
                dogImages.addAll(respuesta)
            }
            adapter.message = dogImages
            adapter.notifyDataSetChanged()
        }

        Log.d("estado ", "3")
        quoteViewModel.isLoading.observe(this) {
            binding.loading.isVisible = it
        }
        Log.d("estado ", "4")
        quoteViewModel.errorLiveData.observe(this) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
        Log.d("estado ", "5")

        // Configurar el adapter con una lista vacía inicialmente
        listadoRazasAdapter = ListadoRazasAdapter(this, R.layout.simple_dropdown_item_1line, emptyList())

        // Observar las actualizaciones en la lista de sugerencias
        quoteViewModel.suggestions.observe(this, Observer { query ->
            listadoRazasAdapter.clear()
            listadoRazasAdapter.addAll(suggestions)
            listadoRazasAdapter.notifyDataSetChanged()
        })

    }

    private fun initRecyclerView() {
        Log.d("estado ", "1")
        adapter= QuoteAdapter(dogImages)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            quoteViewModel.onCreate(query,  context)
            hideKeyBoard()
        }
        return true
    }

    private fun hideKeyBoard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}


