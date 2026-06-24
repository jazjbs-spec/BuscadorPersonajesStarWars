package com.jazminbustillos.starwarsfinder.model

data class ResultadoApi(
    val exito: Boolean,
    val personajes: List<Personaje>
)