package com.example.myapplication.ui.RecycleView

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class QuoteViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemDogBinding.bind(view)
    fun bind(image:String){
        Log.d("estado ", "holder")
        Log.d("estado ", "$image")
       Picasso.get().load(image).into(binding.ivDog)        //gracias a picasso, convertimos la url en imagen
    }
}