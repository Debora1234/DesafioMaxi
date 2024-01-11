package com.example.myapplication.di

import com.example.myapplication.data.network.Service
import com.example.myapplication.data.network.SimuladorInterceptorQuotes
import com.example.myapplication.data.network.SimuladorInterceptorRazas
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    //normal
    single {provideGetRetrofit() }
    single { Service(get()) }

    //interceptor RAZAS
    single { provideSimuladorInterceptorRazas() }
    single { provideOkHttpClientRazas(get()) }
    single (named("retrofitSimuladorRazas")) { provideSimuladorRazasRetrofit(get()) }
    // Proporciona la instancia de Service con el nombre "serviceSimulador" y pasa el Retrofit simulado
    single(named("serviceSimuladorRazas")) { Service(get<Retrofit>(named("retrofitSimuladorRazas"))) }

    //interceptor QUOTES = IMAGENES
    single { provideSimuladorInterceptorQuotes() }
    single { provideOkHttpClientQuotes(get()) }
    single (named("retrofitSimuladorQuotes")) { provideSimuladorQuotesRetrofit(get()) }
    // Proporciona la instancia de Service con el nombre "serviceSimulador" y pasa el Retrofit simulado
    single(named("serviceSimuladorQuotes")) { Service(get<Retrofit>(named("retrofitSimuladorQuotes"))) }
}

//normal
fun provideGetRetrofit(): Retrofit {
   return Retrofit.Builder()
       .baseUrl("https://dog.ceo/api/")
       .addConverterFactory(GsonConverterFactory.create())
       .build()
}


//interceptor RAZAS
fun provideSimuladorInterceptorRazas(): SimuladorInterceptorRazas {
    return SimuladorInterceptorRazas()
}
fun provideOkHttpClientRazas(simuladorInterceptor: SimuladorInterceptorRazas): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(simuladorInterceptor)
        .build()
}
fun provideSimuladorRazasRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}



//interceptor QUOTES = IMAGENES
fun provideSimuladorInterceptorQuotes(): SimuladorInterceptorQuotes {
    return SimuladorInterceptorQuotes()
}
fun provideOkHttpClientQuotes(simuladorInterceptor: SimuladorInterceptorQuotes): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(simuladorInterceptor)
        .build()
}
fun provideSimuladorQuotesRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}