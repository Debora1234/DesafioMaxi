package com.example.myapplication.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/*

Al hacer la inyección de dependencia no se usa mas el objeto RetrofitHelper

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}*/