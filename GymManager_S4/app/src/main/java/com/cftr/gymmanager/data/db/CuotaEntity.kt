package com.cftr.gymmanager.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cuotas",
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
data class CuotaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val socioId: Int,
    val mes: String,
    val importe: Double,
    val pagada: Boolean = false,
    val fechaPago: String? = null
)
