package com.example.myapplication.di

import com.example.myapplication.ui.viewModel.QuoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        QuoteViewModel(get(), get())
    }
}