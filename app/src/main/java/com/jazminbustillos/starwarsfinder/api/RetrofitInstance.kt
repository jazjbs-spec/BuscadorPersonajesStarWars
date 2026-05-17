package com.jazminbustillos.starwarsfinder.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: SwapiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://swapi.py4e.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SwapiService::class.java)
    }
}