package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wordTable")
class Word(@ColumnInfo(name = "word") val word :String)
{
    @PrimaryKey(autoGenerate = true) var id = 0
}