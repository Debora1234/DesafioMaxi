package com.example.myapplication.di

import com.example.myapplication.data.Repository
import com.example.myapplication.data.network.Service
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    single { Repository(get()) }

    single (named("repositorySimuladorRazas")){ Repository(get<Service>(named("serviceSimuladorRazas"))) }

    single (named("repositorySimuladorQuotes")){ Repository(get<Service>(named("serviceSimuladorQuotes"))) }
}