package com.example.miprimeraapp.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{

    private const val BASE_URL = "http://172.24.6.14/api_rest_1/"
    //poner ip actual

    val instance: ApiService by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}