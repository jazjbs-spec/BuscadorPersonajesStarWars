package com.jazminbustillos.starwarsfinder.model

data class SwapiResponse(
    val results: List<SwapiCharacter>
)

data class SwapiCharacter(
    val name: String,
    val height: String
)