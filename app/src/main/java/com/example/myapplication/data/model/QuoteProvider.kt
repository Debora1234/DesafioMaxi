package com.example.myapplication.data.model

import kotlin.text.Typography.quote

class QuoteProvider {
    //si le pongo compain objet, puedo acceder a la clase desde otro lado, al ponerle privado a las opciones, solo puedo acceder al metodo
    companion object{
                var quotes: List<QuoteModel> = emptyList()
        }

}

