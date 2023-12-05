package com.example.myapplication.ui.RecycleView.Imagenes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.model.Quote


class QuoteAdapter (var message: List<Quote>) : RecyclerView.Adapter<QuoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return QuoteViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }
    //metodo nos devuelve la cantidad de imagenes que tiene nuestro elemento
    override fun getItemCount(): Int = message.size

    //metodo para actualizar la lista
    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = message[position]
        holder.bind(item.message)  //llamamos al metodo que creamos en QuoteViewHolder
    }
}
