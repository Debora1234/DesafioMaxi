package com.example.myapplication.ui.RecycleView.ListadoRazas

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemDogBinding
import com.example.myapplication.databinding.ItemListadoRazasBinding
import com.example.myapplication.domain.model.Quote
import com.example.myapplication.domain.model.Raza
import com.squareup.picasso.Picasso
import kotlin.text.Typography.quote


class ListadoRazasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemListadoRazasBinding.bind(view)
    fun render(razaModelo: Raza){
        binding.tvListadoRazas.text = razaModelo.razas
        Log.d("estados", "entro en el viewholder, con el valor $razaModelo.razas")
    }
}

