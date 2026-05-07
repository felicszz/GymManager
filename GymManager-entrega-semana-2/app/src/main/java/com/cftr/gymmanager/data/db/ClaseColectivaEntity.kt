package com.cftr.gymmanager.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clases_colectivas")
data class ClaseColectivaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val horario: String,
    val aforoMaximo: Int,
    val aforoActual: Int = 0
)
