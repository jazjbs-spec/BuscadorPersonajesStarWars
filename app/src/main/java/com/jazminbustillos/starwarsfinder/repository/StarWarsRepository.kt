package com.jazminbustillos.starwarsfinder.repository

import com.jazminbustillos.starwarsfinder.R
import com.jazminbustillos.starwarsfinder.api.RetrofitInstance
import com.jazminbustillos.starwarsfinder.model.Personaje

class StarWarsRepository {

    suspend fun obtenerPersonajes(): List<Personaje> {

        RetrofitInstance.api.getCharacters()

        return listOf(
            Personaje("Luke Skywalker", "Jedi", "23 años", "172 cm", "Héroe de la Rebelión", R.drawable.luke, true),
            Personaje("Obi-Wan Kenobi", "Maestro Jedi", "57 años", "182 cm", "Mentor legendario", R.drawable.obiwan, true),
            Personaje("Ahsoka Tano", "Jedi", "36 años", "188 cm", "Guerrera independiente", R.drawable.ahsoka, true),
            Personaje("Mace Windu", "Maestro Jedi", "53 años", "188 cm", "Jedi poderoso", R.drawable.mace, true),
            Personaje("Anakin Skywalker", "Jedi Caído", "45 años", "188 cm", "Elegido de la Fuerza", R.drawable.anakin, true),

            Personaje("Darth Vader", "Sith", "45 años", "202 cm", "Señor oscuro temido", R.drawable.vader, false),
            Personaje("Darth Maul", "Sith", "31 años", "175 cm", "Guerrero brutal", R.drawable.maul, false),
            Personaje("Kylo Ren", "Sith", "30 años", "189 cm", "Heredero del caos", R.drawable.kylo, false),
            Personaje("Conde Dooku", "Sith", "83 años", "193 cm", "Maestro oscuro elegante", R.drawable.dooku, false),
            Personaje("General Grievous", "Sith", "Unknown", "216 cm", "Cazador de Jedi", R.drawable.grievous, false)
        )
    }
}