package com.example.myapplication.domain.model

import com.example.myapplication.data.database.entities.QuoteEntity
import com.example.myapplication.data.modelApi.QuoteModel

data class Quote (
    val status: String,
    val raza:String,
    val message:String
)

//fun QuoteModel.toDomain(raza: String) : List<Quote> = Quote(status, message)
fun QuoteEntity.toDomain() = Quote(status, raza, message)

