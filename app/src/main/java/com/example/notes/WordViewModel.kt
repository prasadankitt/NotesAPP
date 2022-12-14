package com.example.notes

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository
    val allwords: LiveData<List<Word>>

    init {
        val dao = WordRoomDatabase.getDatabase(application).wordDao()
        repository = WordRepository(dao)
        allwords = repository.allWords
    }

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
    fun delete(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(word)
    }
}
