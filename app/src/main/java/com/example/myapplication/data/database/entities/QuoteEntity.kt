package com.example.myapplication.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.model.Quote

@Entity(tableName = "quote_table")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id") val id: Int=0,
    @ColumnInfo(name="status") val status: String,
    @ColumnInfo(name="message")val message: List<String>
    )

fun Quote.toDatabase()= QuoteEntity(status = status, message = message)