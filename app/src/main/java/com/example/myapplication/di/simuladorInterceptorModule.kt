

package com.example.myapplication.di


import android.content.Context
import com.example.myapplication.data.network.SimuladorInterceptorUtils
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val simuladorInterceptorModule = module {
    single(named("retrofitSimulador")) { provideSimuladorInterceptor(androidContext()) }
    single { provideOkHttpClient(get(named("retrofitSimulador"))) }
    single(named("retrofitSimulador")) { provideSimuladorRetrofit(get(named("retrofitSimulador"))) }
}

fun provideSimuladorInterceptor(context: Context): SimuladorInterceptorUtils {
    return SimuladorInterceptorUtils(context)
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