package com.example.myapplication.data.model

import android.widget.Toast
import com.example.myapplication.ui.RecycleView.QuoteAdapter
import kotlin.text.Typography.quote

class QuoteProvider {

        companion object {
            var quotes: MutableList<QuoteModel> = mutableListOf()
        }
    }
