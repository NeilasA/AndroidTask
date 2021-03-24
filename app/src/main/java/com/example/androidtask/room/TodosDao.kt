package com.example.androidtask.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidtask.api.Data
import com.example.androidtask.api.TodosJson

@Dao
interface TodosDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData ( data: Data)

    @Query("SELECT * FROM todos_table ORDER BY id ASC")
    fun readTodosData(): LiveData<List<Data>>
}