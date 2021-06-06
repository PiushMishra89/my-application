package com.test.myapplication.network

import com.test.myapplication.network.Response.ApiResponse
import com.test.myapplication.network.Response.Product
import com.test.myapplication.network.Response.Variant
import retrofit2.Call
import retrofit2.http.GET

interface MyApi {
    @GET("json")
    fun getAllShoping(): Call<ApiResponse>

    @GET("json")
    fun getVariants():Call<Product>
}