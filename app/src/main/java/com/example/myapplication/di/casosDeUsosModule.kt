package com.example.myapplication.di

import com.example.myapplication.domain.GetQuotesUseCase
import com.example.myapplication.domain.GetRazasUseCase
import org.koin.dsl.module

val casosDeUsosModule = module {
    single { GetRazasUseCase(get()) }
    factory { GetQuotesUseCase(get()) }
}