package com.example.myapplication.domain.model

import com.example.myapplication.data.database.entities.QuoteEntity
import com.example.myapplication.data.model.QuoteModel

data class Quote (
    val status: String,    //era author
    val raza:String,
    val message:String  //era quote
)

//fun QuoteModel.toDomain(raza: String) : List<Quote> = Quote(status, message)
fun QuoteEntity.toDomain() = Quote(status, raza, message)