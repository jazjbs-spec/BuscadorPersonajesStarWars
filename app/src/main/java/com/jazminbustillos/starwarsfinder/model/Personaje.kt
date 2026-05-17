package com.jazminbustillos.starwarsfinder.model

data class Personaje(
    val nombre: String,
    val subtitulo: String,
    val edad: String,
    val altura: String,
    val descripcion: String,
    val imagen: Int,
    val esJedi: Boolean
)