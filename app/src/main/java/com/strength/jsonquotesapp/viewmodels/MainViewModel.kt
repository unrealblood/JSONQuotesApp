package com.strength.jsonquotesapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.strength.jsonquotesapp.data.Quote

class MainViewModel(private val appContext : Context) : ViewModel() {
    private lateinit var quoteList : Array<Quote>
    private var currentQuoteIndex : Int = 0

    init {
        currentQuoteIndex = 0

        quoteList = loadQuotesFromAssetsFolder()
    }

    private fun loadQuotesFromAssetsFolder() : Array<Quote> {
        val inputStream = appContext.assets.open("quotes_bk.json")
        val fileSize = inputStream.available()
        val buffer = ByteArray(fileSize)

        inputStream.read(buffer)
        inputStream.close()

        val fileJsonString = String(buffer, Charsets.UTF_8)
        val gsonFile = Gson()

        return gsonFile.fromJson(fileJsonString, Array<Quote>::class.java)
    }

    fun getQuote() : Quote {
        return quoteList[currentQuoteIndex]
    }

    fun nextQuote() : Quote {
        currentQuoteIndex++
        return quoteList[currentQuoteIndex]
    }

    fun previousQuote() : Quote {
        currentQuoteIndex--
        return quoteList[currentQuoteIndex]
    }
}