package com.example.myapplication.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.data.model.ListaRazasModel
import com.example.myapplication.domain.model.Quote
import com.example.myapplication.domain.model.Raza

@Entity(tableName = "razas_table")
data class RazasEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val razas : String
)
fun Raza.toDataBase() = RazasEntity(razas = razas)
//fun ListaRazasModel.toDataBase() = RazasEntity(razas = razas)



/*fun ListaRazasModel.toRazasEntity(): RazasEntity {
    // Convertir el mapa a una cadena de texto
    val razas = buildString {
        for ((key, values) in message) {
            append("$key: ${values.joinToString(", ")}\n")
        }
    }

    return RazasEntity(razas = razas)
}*/