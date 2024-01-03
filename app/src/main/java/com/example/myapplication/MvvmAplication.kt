package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.casosDeUsosModule
import com.example.myapplication.di.networkModule
import com.example.myapplication.di.repositoryModule
import com.example.myapplication.di.viewModelModule

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MvvmAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Iniciar Koin
        startKoin {
            androidLogger()
            androidContext(this@MvvmAplication)
            modules(networkModule, casosDeUsosModule, viewModelModule, repositoryModule)
        }
    }
}

