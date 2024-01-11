package com.example.myapplication.di

import com.example.myapplication.data.Repository
import com.example.myapplication.data.network.Service
import com.example.myapplication.domain.GetQuotesUseCase
import com.example.myapplication.domain.GetRazasUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject

val casosDeUsosModule = module {
    single { GetRazasUseCase(get<Repository>(named("repositorySimulador")))}
    single { GetQuotesUseCase(get()) }
}


