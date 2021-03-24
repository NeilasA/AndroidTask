package com.example.androidtask

import com.example.androidtask.api.TodosJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {

    @GET("/public-api/todos")
    fun getTodos(): Call<TodosJson>
}