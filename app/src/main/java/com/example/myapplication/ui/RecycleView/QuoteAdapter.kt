package com.example.myapplication.ui.RecycleView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class QuoteAdapter (private var images: List<String>) : RecyclerView.Adapter<QuoteViewHolder>() {
       //metodo
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            Log.d("estado ", "adapter")
            return QuoteViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
        }
        //metodo nos devuelve la cantidad de imagenes que tiene nuestro elemento
        override fun getItemCount(): Int = images.size
        //metodo para actualizar la lista
        override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
            val item = images[position]
            holder.bind(item)  //llamamos al metodo que creamos en QuoteViewHolder
        }
 }