package com.example.androidtask.api

data class Pagination(
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)