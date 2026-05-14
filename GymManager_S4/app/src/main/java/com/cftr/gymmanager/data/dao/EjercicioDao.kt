package com.cftr.gymmanager.data.dao

import androidx.room.*
import com.cftr.gymmanager.data.db.EjercicioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EjercicioDao {

    @Query("SELECT * FROM ejercicios WHERE rutinaId = :rutinaId ORDER BY id ASC")
    fun getEjerciciosByRutina(rutinaId: Int): Flow<List<EjercicioEntity>>

    @Query("SELECT * FROM ejercicios WHERE id = :id")
    suspend fun getEjercicioById(id: Int): EjercicioEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEjercicio(ejercicio: EjercicioEntity): Long

    @Update
    suspend fun updateEjercicio(ejercicio: EjercicioEntity)

    @Delete
    suspend fun deleteEjercicio(ejercicio: EjercicioEntity)
}
