package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import retrofit2.Retrofit
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject


class MvvmAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Iniciar Koin
        startKoin {
            androidLogger()
            androidContext(this@MvvmAplication)
            modules(
                networkModule,
                casosDeUsosModule,
                viewModelModule,
                repositoryModule
          //      simuladorInterceptorModule
            )
        }

    }

}

