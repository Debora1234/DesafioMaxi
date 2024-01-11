package com.example.myapplication.di

import com.example.myapplication.data.Repository
import com.example.myapplication.data.network.Service
import com.example.myapplication.domain.GetQuotesUseCase
import com.example.myapplication.domain.GetRazasUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject

val casosDeUsosModule = module {

    /*
    //caso de usos normales, sin interceptores
    single { GetRazasUseCase(get())}
    single { GetQuotesUseCase(get()) }
    */

    /*
    //caso de usos con interceptor en las razas
    single { GetRazasUseCase(get<Repository>(named("repositorySimuladorRazas")))}
    single { GetQuotesUseCase(get()) }
    */


    //caso de usos con interceptor en las imagenes (quote)
    single { GetRazasUseCase(get())}
    single { GetQuotesUseCase(get<Repository>(named("repositorySimuladorQuotes"))) }

}


