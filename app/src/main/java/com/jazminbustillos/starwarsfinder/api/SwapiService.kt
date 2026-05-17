package com.jazminbustillos.starwarsfinder.api

import com.jazminbustillos.starwarsfinder.model.SwapiResponse
import retrofit2.http.GET

interface SwapiService {

    @GET("people/")
    suspend fun getCharacters(): SwapiResponse
}