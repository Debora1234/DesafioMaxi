package com.example.myapplication.ui.view

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.model.QuoteModel
import com.example.myapplication.data.model.QuoteProvider.Companion.quotes
import com.example.myapplication.ui.RecycleView.QuoteAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.GetQuotesUseCase
import com.example.myapplication.ui.viewModel.QuoteViewModel

class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel : QuoteViewModel by viewModels()         //conectamos el viewmodel al activity

    private lateinit var  adapter: QuoteAdapter
    private val dogImages = mutableListOf<String>() // ponemos una lista de mutable para ir modificando el listado cada vez que cambiamos de raza

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("estado ", "1) activity")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.svDogs.setOnQueryTextListener(this)
        initRecyclerView()

        quoteViewModel.dogImagesLiveData.observe(this) { images ->
            Log.d("estado ", "quoteViewModel.dogImagesLiveData.observe: $images")
            dogImages.clear()
            dogImages.addAll(images)
            adapter.notifyDataSetChanged()
        }

        quoteViewModel.isLoading.observe(this) {
            Log.d("estado ", "quoteViewModel.isLoading.observe")
            binding.loading.isVisible = it
        }

        quoteViewModel.errorLiveData.observe(this) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun initRecyclerView() {
        Log.d("estado ", "entro recyclerview")
        adapter = QuoteAdapter(dogImages)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            quoteViewModel.onCreate(query)
            Log.d("estado ", "cambie la query")
            hideKeyBoard()
        }
        return true
    }

    private fun hideKeyBoard() { //funcion para ocultar el teclado cada vez que le damos clic a buscar
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}





