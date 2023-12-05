package com.example.myapplication.ui.RecycleView.ListadoRazas

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.model.Quote
import com.example.myapplication.domain.model.Raza
import com.google.gson.Gson

private var ultimaRazaConocida: String? = null

class ListadoRazasAdapter(var listadoRazasBaseDatosLocal: List<Raza>) : RecyclerView.Adapter<ListadoRazasViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListadoRazasViewHolder {
        Log.d("estados", "onCreateViewHolder")
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListadoRazasViewHolder(layoutInflater.inflate(R.layout.item_listado_razas, parent, false))
    }
    override fun onBindViewHolder(holder: ListadoRazasViewHolder, position: Int) {
        Log.d("estados", "onBindViewHolder")
        val item = listadoRazasBaseDatosLocal[position]
        holder.render(item)
        Log.d("estadoss", "entro a viewholder ${item.razas}")

    }

    override fun getItemCount(): Int {
        Log.d("estados", "getItemCount()")
        return listadoRazasBaseDatosLocal.size
    }


}
