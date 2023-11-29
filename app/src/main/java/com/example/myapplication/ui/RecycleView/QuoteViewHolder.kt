package com.example.myapplication.ui.RecycleView

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemDogBinding
import com.example.myapplication.domain.model.Quote
import com.squareup.picasso.Picasso
import kotlin.text.Typography.quote

class QuoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemDogBinding.bind(view)

    fun bind(quote: String) {
        Log.d("estado ", "holder")
        Log.d("estado ", "$quote")

        // Verifica si la lista de mensajes no está vacía
        if (quote.isNotEmpty()) {
            // Tomar el primer mensaje como la URL de la imagen
            val imageUrl = quote
            Picasso.get().load(imageUrl).into(binding.ivDog)
        }
    }
}
