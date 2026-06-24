package com.jazminbustillos.starwarsfinder.repository

import com.jazminbustillos.starwarsfinder.R
import com.jazminbustillos.starwarsfinder.api.RetrofitInstance
import com.jazminbustillos.starwarsfinder.model.Personaje
import com.jazminbustillos.starwarsfinder.model.ResultadoApi

class StarWarsRepository {

    suspend fun obtenerPersonajes(): ResultadoApi {

        var conexionExitosa = false

        try {
            RetrofitInstance.api.getCharacters()
            conexionExitosa = true
        } catch (_: Exception) {
            conexionExitosa = false
        }

        return ResultadoApi(
            exito = conexionExitosa,
            personajes = listOf(

                Personaje(
                    "Luke Skywalker",
                    "Jedi",
                    "23 años",
                    "172 cm",
                    "Héroe de la Rebelión y usuario de la Fuerza.",
                    R.drawable.luke,
                    true
                ),

                Personaje(
                    "Obi-Wan Kenobi",
                    "Maestro Jedi",
                    "57 años",
                    "182 cm",
                    "Legendario maestro Jedi y mentor de Luke.",
                    R.drawable.obiwan,
                    true
                ),

                Personaje(
                    "Ahsoka Tano",
                    "Jedi",
                    "36 años",
                    "188 cm",
                    "Guerrera independiente entrenada por Anakin.",
                    R.drawable.ahsoka,
                    true
                ),

                Personaje(
                    "Mace Windu",
                    "Maestro Jedi",
                    "53 años",
                    "188 cm",
                    "Uno de los Jedi más poderosos de la Orden.",
                    R.drawable.mace,
                    true
                ),

                Personaje(
                    "Anakin Skywalker",
                    "Jedi Caído",
                    "45 años",
                    "188 cm",
                    "El Elegido que terminó convirtiéndose en Darth Vader.",
                    R.drawable.anakin,
                    true
                ),

                Personaje(
                    "Darth Vader",
                    "Sith",
                    "45 años",
                    "202 cm",
                    "Señor Oscuro de los Sith.",
                    R.drawable.vader,
                    false
                ),

                Personaje(
                    "Darth Maul",
                    "Sith",
                    "31 años",
                    "175 cm",
                    "Guerrero Sith famoso por su sable doble.",
                    R.drawable.maul,
                    false
                ),

                Personaje(
                    "Kylo Ren",
                    "Lado Oscuro",
                    "30 años",
                    "189 cm",
                    "Líder de la Primera Orden.",
                    R.drawable.kylo,
                    false
                ),

                Personaje(
                    "Conde Dooku",
                    "Sith",
                    "83 años",
                    "193 cm",
                    "Antiguo Jedi convertido al lado oscuro.",
                    R.drawable.dooku,
                    false
                ),

                Personaje(
                    "General Grievous",
                    "Lado Oscuro",
                    "Desconocida",
                    "216 cm",
                    "Temido cazador de Jedi.",
                    R.drawable.grievous,
                    false
                )
            )
        )
    }
}