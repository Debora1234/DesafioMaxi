package com.example.myapplication.ui.RecycleView.ListadoRazas

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemDogBinding
import com.example.myapplication.databinding.ItemListadoRazasBinding
import com.example.myapplication.domain.model.Quote
import com.example.myapplication.domain.model.Raza
import com.example.myapplication.ui.view.MainActivity
import com.example.myapplication.ui.view.MainActivity2
import com.squareup.picasso.Picasso
import kotlin.text.Typography.quote


class ListadoRazasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemListadoRazasBinding.bind(view)


    fun render(raza: Raza){
        binding.tvListadoRazas.text = raza.razas
        Log.d("estado3", "entro en el viewholder, con el valor $raza.razas")
        binding.item.setOnClickListener {
            val query = raza.razas
            val intent = Intent(this.itemView.context, MainActivity2::class.java)
            Log.d("estado3", "$query")
            intent.putExtra("query", query)
            this.itemView.context.startActivity(intent)


        }

        }
    }


