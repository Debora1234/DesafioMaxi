package com.example.myapplication.ui.RecycleView

import android.content.Context
import android.widget.ArrayAdapter

class ListadoRazasAdapter (context: Context, resource: Int, objects: List<String>) :
    ArrayAdapter<String>(context, resource, objects) {

    // Puedes personalizar el adapter según tus necesidades
    // Por ejemplo, puedes cambiar la apariencia de las sugerencias en getView()
}