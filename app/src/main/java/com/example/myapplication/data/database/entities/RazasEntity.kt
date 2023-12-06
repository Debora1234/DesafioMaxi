package com.example.myapplication.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.model.Raza

@Entity(tableName = "razas_table")
data class RazasEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val razas : String
)
fun Raza.toDataBase() = RazasEntity(razas = razas)
