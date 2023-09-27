package com.example.mvvmdemo

import androidx.lifecycle.LiveData

class QuoteRepository(val quoteDAO: QuoteDAO) {

    fun getQuotes(): LiveData<List<Quote>>{
        return quoteDAO.getQuotes()
    }

    suspend fun insertQuote(quote: Quote){
        quoteDAO.insertQuote(quote)
    }
}