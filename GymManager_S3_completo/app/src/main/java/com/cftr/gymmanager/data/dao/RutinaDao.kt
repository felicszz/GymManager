package com.cftr.gymmanager.data.dao

import androidx.room.*
import com.cftr.gymmanager.data.db.RutinaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RutinaDao {

    @Query("SELECT * FROM rutinas ORDER BY nombre ASC")
    fun getAllRutinas(): Flow<List<RutinaEntity>>

    @Query("SELECT * FROM rutinas WHERE socioId = :socioId")
    fun getRutinasBySocio(socioId: Int): Flow<List<RutinaEntity>>

    @Query("SELECT * FROM rutinas WHERE id = :id")
    suspend fun getRutinaById(id: Int): RutinaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRutina(rutina: RutinaEntity): Long

    @Update
    suspend fun updateRutina(rutina: RutinaEntity)

    @Delete
    suspend fun deleteRutina(rutina: RutinaEntity)
}
