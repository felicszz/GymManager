package com.cftr.gymmanager.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "socios")
data class SocioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val apellidos: String,
    val email: String,
    val telefono: String,
    val fechaAlta: String,
    val activo: Boolean = true
)
