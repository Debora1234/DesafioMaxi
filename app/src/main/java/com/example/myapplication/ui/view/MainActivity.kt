package com.example.myapplication.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.viewModel.QuoteViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    public val query = "terrier-kerryblue"


    /*conectamos el viewmodel al activity*/
    private val quoteViewModel : QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate(query)
        initRecyclerView()


      /*
        // todo lo q esta aca adentro esta enganchado al lifedata
        quoteViewModel.quoteModel.observe(this, Observer {
            binding.txtCotizaciones.text = it.quote
           binding.tvAuthor.text = it.author
        })
*/
//       binding.viewPrincipal.setOnClickListener { quoteViewModel.randomQuote() }
    }

    private fun initRecyclerView(){

    }
}