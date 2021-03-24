package com.example.androidtask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.androidtask.api.Data
import com.example.androidtask.room.TodosDatabase
import com.example.androidtask.room.TodosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class TodosViewModel(application: Application): AndroidViewModel(application) {



    val readAllData: LiveData<List<Data>>
    private val repository: TodosRepository

    init {
        val todosDao = TodosDatabase.getDatabase(application).todosDao()
        repository = TodosRepository(todosDao)
        readAllData = repository.readAllData
    }

    fun insertData(data : Data){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertData(data)
        }
    }
}