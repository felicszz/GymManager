package com.cftr.gymmanager.model

data class Cuota(
    val id: Int = 0,
    val socioId: Int,
    val mes: String,
    val importe: Double,
    val pagada: Boolean = false,
    val fechaPago: String? = null
)
