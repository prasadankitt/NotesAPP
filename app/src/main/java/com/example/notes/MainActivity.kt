package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), IWordRVAdapter {
    lateinit var viewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  recyclerView = findViewById<RecyclerView>(R.id.wordRV)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = WordRVAdapter(this,this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[WordViewModel::class.java]
        viewModel.allwords.observe(this,Observer{ list ->
            list?.let {
                adapter.updateList(it)
            }
        })
    }

    override fun onItemClicked(word: Word) {
        viewModel.delete(word)
        Toast.makeText(this,"${word.word} Deleted",Toast.LENGTH_LONG).show()
    }

    fun submitWord(view : View) {
        val inputData = findViewById<EditText>(R.id.input)
        val wordText =  inputData.text.toString()
        if(wordText.isNotEmpty())
        {
            viewModel.insert(Word(wordText))
            Toast.makeText(this,"$wordText Inserted",Toast.LENGTH_LONG).show()
        }
    }

}