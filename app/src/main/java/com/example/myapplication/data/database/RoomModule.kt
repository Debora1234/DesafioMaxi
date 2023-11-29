package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.database.dao.QuoteDao

object RoomModule {

    const val QUOTE_DATABASE_NAME = "quote_database"
    fun provideRoom(context: Context): QuoteDatabase {
    return Room.databaseBuilder(context, QuoteDatabase::class.java, "quote_database")
        .build()

    }

    fun providerDao(database: QuoteDatabase): QuoteDao {
        return database.GetQuoteDao()
    }
}