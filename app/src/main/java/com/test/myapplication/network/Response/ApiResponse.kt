package com.test.myapplication.network.Response

data class ApiResponse(
    val categories: List<Category>,
    val rankings: List<Ranking>
)