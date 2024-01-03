package com.example.myapplication.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.Activity2MainBinding
import com.example.myapplication.domain.model.Quote
import com.example.myapplication.ui.RecycleView.Imagenes.QuoteAdapter
import com.example.myapplication.ui.viewModel.QuoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity2 : AppCompatActivity() {


    private lateinit var binding: Activity2MainBinding
    private val quoteViewModel: QuoteViewModel by viewModel()


    private lateinit var adapterImagenes: QuoteAdapter
    private val dogImages = mutableListOf<Quote>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = Activity2MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterImagenes= QuoteAdapter(dogImages)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter =  adapterImagenes



        quoteViewModel.dogImagesLiveData.observe( this) { respuesta ->
            Log.d("estado3", " dogImagesLiveData ")
            Log.d("estado3", " dogImagesLiveData $respuesta ")
            dogImages.clear()
            if (respuesta != null) {
                dogImages.addAll(respuesta)
            }
            adapterImagenes.message = dogImages
            Log.d("estado3", "respuesta entro a dogImagesLiveData $dogImages")
            adapterImagenes.notifyDataSetChanged()
        }


        var query = intent.extras?.getString("query")
        if (!query.isNullOrEmpty()) {
            quoteViewModel.onCreateImagenes(query,  this)
            Log.d("estado3", " la query no fue nula, le mando $query")
        }

        binding.buttonVolver.setOnClickListener(){
            onBackPressed()
        }

    }


}