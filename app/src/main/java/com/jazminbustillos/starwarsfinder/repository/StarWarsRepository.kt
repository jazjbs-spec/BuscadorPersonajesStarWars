package com.jazminbustillos.starwarsfinder.repository

import com.jazminbustillos.starwarsfinder.model.Personaje
import com.jazminbustillos.starwarsfinder.model.ResultadoApi
import com.jazminbustillos.starwarsfinder.api.RetrofitInstance

class StarWarsRepository {

    suspend fun obtenerPersonajes(): ResultadoApi {

        return try {

            val respuesta = RetrofitInstance.api.getCharacters()

            val personajes = respuesta.results.mapIndexed { index, personaje ->

                Personaje(
                    nombre = personaje.name,
                    altura = personaje.height,
                    imagen = "https://starwars-visualguide.com/assets/img/characters/${index + 1}.jpg"
                )
            }

            ResultadoApi(
                exito = true,
                personajes = personajes
            )

        } catch (e: Exception) {

            ResultadoApi(
                exito = false,
                personajes = emptyList()
            )
        }
    }
}