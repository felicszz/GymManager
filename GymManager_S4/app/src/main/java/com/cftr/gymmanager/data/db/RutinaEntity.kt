package com.cftr.gymmanager.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "rutinas",
    foreignKeys = [
        ForeignKey(
            entity = SocioEntity::class,
            parentColumns = ["id"],
            childColumns = ["socioId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("socioId")]
)
data class RutinaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val socioId: Int
)
