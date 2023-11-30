package com.example.myapplication.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.model.Quote


@Entity(tableName = "quote_table")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val status: String,
    val raza: String,
    val message: String
    )

fun Quote.toDatabase()= QuoteEntity(status = status, raza = raza, message = message)