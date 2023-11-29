package com.example.myapplication.ui.RecycleView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.model.Quote

class QuoteAdapter(private var quotes: ArrayList<String>) : RecyclerView.Adapter<QuoteViewHolder>() {
    //var quotes: ArrayList<String> = ArrayList()
     /*   set(value) {
            field = value
            notifyDataSetChanged()
        }*/

    fun setitems(quotes: ArrayList<String>) {
        this.quotes = quotes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return QuoteViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }

    override fun getItemCount(): Int = quotes.size

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = quotes[position]
        holder.bind(item)
    }
}
