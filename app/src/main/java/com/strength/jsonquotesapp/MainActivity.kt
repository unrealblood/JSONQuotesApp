package com.strength.jsonquotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.strength.jsonquotesapp.data.Quote
import com.strength.jsonquotesapp.viewmodels.MainViewModel
import com.strength.jsonquotesapp.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    //view models
    private lateinit var mainViewModel: MainViewModel

    //views
    private lateinit var quoteTextTextView : TextView
    private lateinit var quoteAuthorTextView: TextView
    private lateinit var nextQuoteButton: Button
    private lateinit var previousQuoteButton: Button
    private lateinit var shareButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(applicationContext)).get(MainViewModel::class.java)

        quoteTextTextView = findViewById(R.id.quote_text_MA)
        quoteAuthorTextView = findViewById(R.id.quote_author_name_text_MA)
        nextQuoteButton = findViewById(R.id.next_quote_button_MA)
        previousQuoteButton = findViewById(R.id.previous_quote_button_MA)
        shareButton = findViewById(R.id.share_quote_floating_action_button_MA)

        setQuote(mainViewModel.getQuote())

        previousQuoteButton.setOnClickListener { setQuote(mainViewModel.previousQuote()) }
        nextQuoteButton.setOnClickListener { setQuote(mainViewModel.nextQuote()) }
        shareButton.setOnClickListener { shareQuote() }
    }

    private fun setQuote(quote: Quote) {
        quoteTextTextView.text = quote.quoteText
        quoteAuthorTextView.text = quote.quoteAuthor
    }

    private fun shareQuote() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
        shareIntent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().quoteText)

        startActivity(shareIntent)
    }
}