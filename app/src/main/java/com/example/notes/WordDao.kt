package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*

//we can implement DAO using interface or class
//suspend is modifier same as other coroutines but main feature is that it can use other suspend
//functions inside that function like delay,suspend functions can be paused and resume later
@Dao
interface WordDao {
    //here OnConflictStrategy is used to ignore inserting of a word which is already present
    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word:Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("Select * from wordTable order by id ASC")
    fun getAlphabetizedWords() : LiveData<List<Word>>
}