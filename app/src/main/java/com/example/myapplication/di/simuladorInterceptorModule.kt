

package com.example.myapplication.di


import com.example.myapplication.data.network.SimuladorInterceptorUtils
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val simuladorInterceptorModule = module {
    single { SimuladorInterceptorUtils(androidContext()) }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<SimuladorInterceptorUtils>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/") // Puedes cambiar esta URL según sea necesario
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
}