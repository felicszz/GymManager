package com.cftr.gymmanager.model

data class Socio(
    val id: Int = 0,
    val nombre: String,
    val apellidos: String,
    val email: String,
    val telefono: String,
    val fechaAlta: String,
    val activo: Boolean = true
)
