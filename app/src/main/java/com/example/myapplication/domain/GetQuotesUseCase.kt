package com.example.myapplication.domain

import com.example.myapplication.data.QuoteRepository
import com.example.myapplication.data.model.QuoteModel

class GetQuotesUseCase {
    private val repository = QuoteRepository()
    suspend operator fun invoke():List<QuoteModel>? = repository.getAllQuotes()

}