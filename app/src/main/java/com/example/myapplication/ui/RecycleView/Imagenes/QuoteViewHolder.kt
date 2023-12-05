package com.example.myapplication.ui.RecycleView.Imagenes

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemDogBinding
import com.example.myapplication.domain.model.Quote
import com.squareup.picasso.Picasso

class QuoteViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemDogBinding.bind(view)
    fun bind(message:String){
        Log.d("estado ", "holder")
        Log.d("estado ", "$message")
        Picasso.get().load(message).into(binding.ivDog)        //gracias a picasso, convertimos la url en imagen
    }
}