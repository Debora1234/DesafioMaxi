package com.example.myapplication.domain.model

import com.example.myapplication.data.database.entities.QuoteEntity
import com.example.myapplication.data.model.QuoteModel
import com.google.gson.annotations.SerializedName

data class Quote (
    val status: String,    //era author
    val message:List<String>   //era quote
)

fun QuoteModel.toDomain() = Quote(status, message)
fun QuoteEntity.toDomain() = Quote(status, message)