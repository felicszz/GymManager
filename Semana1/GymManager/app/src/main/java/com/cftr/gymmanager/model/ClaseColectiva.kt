package com.cftr.gymmanager.model

data class ClaseColectiva(
    val id: Int = 0,
    val nombre: String,
    val horario: String,
    val aforoMaximo: Int,
    val aforoActual: Int = 0
)
