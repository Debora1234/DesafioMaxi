package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.network.SimuladorInterceptorNetworkUtils
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val interceptorNetworkModule = module {
    single { SimuladorInterceptorNetworkUtils(androidContext()) }

    // Define la instancia de OkHttpClient con tu interceptor personalizado
    single { provideHttpClient(androidContext()) }
    single { provideGetRetrofit(get(), getProperty(TEST_SERVER_URL), get()) }
    // Define una función para proporcionar una instancia de Gson
    single { provideGson() }
}

fun provideGetRetrofit(client: OkHttpClient, baseUrl: String, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
    }
// Función para proporcionar una instancia de Gson
fun provideGson(): Gson {
    return Gson()
}

// Función para proporcionar una instancia de OkHttpClient con tu interceptor personalizado
fun provideHttpClient(context: Context): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
    okHttpClientBuilder.addInterceptor(SimuladorInterceptorNetworkUtils(context))
    return okHttpClientBuilder.build()
}