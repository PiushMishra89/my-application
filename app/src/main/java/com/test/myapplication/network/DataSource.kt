package com.test.myapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface DataSource {

    companion object {
        const val BASE_URL = "https://stark-spire-93433.herokuapp.com/"

        operator fun invoke(): MyApi {
           return Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create(MyApi::class.java)
        }
    }
}