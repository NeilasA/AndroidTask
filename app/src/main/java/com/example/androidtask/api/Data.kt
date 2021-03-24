package com.example.androidtask.api

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "todos_table")
data class Data(
    @PrimaryKey (autoGenerate = false)
    val id: Int,
    val title: String,
    val updated_at: String
    //Comment out because i don't need this information for this task
    //val completed: Boolean,
    //val created_at: String,
    //val user_id: Int
)