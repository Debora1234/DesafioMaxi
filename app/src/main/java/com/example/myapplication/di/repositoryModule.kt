package com.example.myapplication.di

import com.example.myapplication.data.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        Repository(get())
    }
}