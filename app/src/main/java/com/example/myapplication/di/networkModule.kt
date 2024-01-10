package com.example.myapplication.di


import com.example.myapplication.data.network.Service
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {provideGetRetrofit() }
    single { Service(get()) }
}

fun provideGetRetrofit(): Retrofit {
   return Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
       .addConverterFactory(GsonConverterFactory.create())
       .build()
}