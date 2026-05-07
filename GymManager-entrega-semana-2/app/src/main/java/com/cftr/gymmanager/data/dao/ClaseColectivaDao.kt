package com.cftr.gymmanager.data.dao

import androidx.room.*
import com.cftr.gymmanager.data.db.ClaseColectivaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClaseColectivaDao {

    @Query("SELECT * FROM clases_colectivas ORDER BY horario ASC")
    fun getAllClases(): Flow<List<ClaseColectivaEntity>>

    @Query("SELECT * FROM clases_colectivas WHERE id = :id")
    suspend fun getClaseById(id: Int): ClaseColectivaEntity?

    @Query("SELECT * FROM clases_colectivas WHERE aforoActual < aforoMaximo ORDER BY horario ASC")
    fun getClasesConPlazas(): Flow<List<ClaseColectivaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClase(clase: ClaseColectivaEntity): Long

    @Update
    suspend fun updateClase(clase: ClaseColectivaEntity)

    @Delete
    suspend fun deleteClase(clase: ClaseColectivaEntity)

    @Query("UPDATE clases_colectivas SET aforoActual = aforoActual + 1 WHERE id = :id AND aforoActual < aforoMaximo")
    suspend fun incrementarAforo(id: Int)

    @Query("UPDATE clases_colectivas SET aforoActual = aforoActual - 1 WHERE id = :id AND aforoActual > 0")
    suspend fun decrementarAforo(id: Int)
}
