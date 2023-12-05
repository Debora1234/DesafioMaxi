package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName


data class ListaRazasModel (
    @SerializedName("message") val message: Map<String, List<String>>,
    @SerializedName("status") val status: String
)