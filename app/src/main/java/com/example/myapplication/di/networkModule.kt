package com.example.myapplication.di


import android.content.Context
import com.example.myapplication.data.network.Service
import com.example.myapplication.data.network.SimuladorInterceptorUtils
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    //normal
    single {provideGetRetrofit() }
    single { Service(get()) }

    //interceptor
    single { provideSimuladorInterceptor() }
    single { provideOkHttpClient(get()) }
    single (named("retrofitSimulador")) { provideSimuladorRetrofit(get()) }
}

//normal
fun provideGetRetrofit(): Retrofit {
   return Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
       .addConverterFactory(GsonConverterFactory.create())
       .build()
}

//interceptor
fun provideSimuladorInterceptor(): SimuladorInterceptorUtils {
    return SimuladorInterceptorUtils()
}
fun provideOkHttpClient(simuladorInterceptor: SimuladorInterceptorUtils): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(simuladorInterceptor)
        .build()
}
fun provideSimuladorRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}