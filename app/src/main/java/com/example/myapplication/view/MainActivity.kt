package com.example.myapplication.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewModel.QuoteViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /*conectamos el viewmodel al activity*/
    private val quoteViewModel : QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // todo lo q esta aca adentro esta enganchado al lifedata
        quoteViewModel.quoteModel.observe(this, Observer {
            binding.txtCotizaciones.text = it.quote
     //       binding.tvAuthor.text = it.author
        })

        binding.viewPrincipal.setOnClickListener { quoteViewModel.randomQuote() }
    }
}