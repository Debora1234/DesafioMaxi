package com.example.myapplication.model

class QuoteProvider {

    //si le pongo compain objet, puedo acceder a la clase desde otro lado, al ponerle privado a las opciones, solo puedo acceder al metodo

    companion object{

        fun random():QuoteModel{
            val position = (0..2).random()
            return quote[position]
        }

        private val quote = listOf<QuoteModel>(
            QuoteModel(
                quote= "primer quote",
                author= "primer autor"
            ),
            QuoteModel(
                quote= "2 quote",
                author= "2 autor"
            ),
            QuoteModel(
                quote= "3 quote",
                author= "3 autor"
            ),
        )
    }


}