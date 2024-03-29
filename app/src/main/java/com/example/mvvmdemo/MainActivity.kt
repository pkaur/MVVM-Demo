package com.example.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val dao = QuoteDatabase.getDatabase(applicationContext).quoteDao()
        val repository = QuoteRepository(dao)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getQuotes().observe(this) {
            binding.quotes = it.toString()
        }
        binding.btnAdd.setOnClickListener {
            val quote = Quote(0,"This is text quote", "PK")
            mainViewModel.insertQuote(quote)
        }
    }
}