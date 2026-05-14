package com.cftr.gymmanager.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "ejercicios",
    foreignKeys = [
        ForeignKey(
            entity = RutinaEntity::class,
            parentColumns = ["id"],
            childColumns = ["rutinaId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("rutinaId")]
)
data class EjercicioEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val rutinaId: Int,
    val nombre: String,
    val series: Int,
    val repeticiones: Int,
    val pesoKg: Double = 0.0,
    val descansoSegundos: Int = 60,
    val notas: String = ""
)
