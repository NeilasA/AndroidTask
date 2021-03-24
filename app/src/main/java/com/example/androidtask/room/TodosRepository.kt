package com.example.androidtask.room

import androidx.lifecycle.LiveData
import com.example.androidtask.api.Data

class TodosRepository(private val todosDao: TodosDao) {

    val readAllData: LiveData<List<Data>> = todosDao.readTodosData()

    suspend fun insertData(data: Data){
        todosDao.insertData(data)
    }
}