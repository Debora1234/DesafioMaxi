package com.example.myapplication.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.database.entities.QuoteEntity

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quote_table WHERE raza = :raza")
    suspend fun getAllQuotes(raza : String): List<QuoteEntity>

    @Insert
    suspend fun insert(quote: QuoteEntity):Long
    @Query("DELETE FROM quote_table")
    suspend fun deleteAllQuotes()
}


