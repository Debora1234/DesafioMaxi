package com.example.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.database.entities.RazasEntity

@Dao
interface RazasDao {
    @Insert
    suspend fun insertRaza(razasEntity: RazasEntity):Long

    @Query("SELECT * FROM razas_table")
    suspend fun getAllRazas(): List<RazasEntity>
}