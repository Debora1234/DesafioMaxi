package com.example.myapplication.domain.model


import com.example.myapplication.data.database.entities.RazasEntity
import com.example.myapplication.data.modelApi.ListaRazasModel



data class Raza(
    val razas:String
)

fun RazasEntity.toDomain() = Raza(razas)


public fun ListaRazasModel.toDomain2(): List<Raza> {
    return message.entries.map { entry ->
        val razas = buildString {
            append("${entry.key} ${entry.value.joinToString(", ")}\n")
        }
        Raza(razas)
    }
}