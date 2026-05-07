package com.cftr.gymmanager.data.dao

import androidx.room.*
import com.cftr.gymmanager.data.db.SocioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SocioDao {

    @Query("SELECT * FROM socios ORDER BY apellidos ASC")
    fun getAllSocios(): Flow<List<SocioEntity>>

    @Query("SELECT * FROM socios WHERE activo = 1 ORDER BY apellidos ASC")
    fun getSociosActivos(): Flow<List<SocioEntity>>

    @Query("SELECT * FROM socios WHERE id = :id")
    suspend fun getSocioById(id: Int): SocioEntity?

    @Query("SELECT * FROM socios WHERE nombre LIKE '%' || :query || '%' OR apellidos LIKE '%' || :query || '%'")
    fun buscarSocios(query: String): Flow<List<SocioEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSocio(socio: SocioEntity): Long

    @Update
    suspend fun updateSocio(socio: SocioEntity)

    @Delete
    suspend fun deleteSocio(socio: SocioEntity)

    @Query("UPDATE socios SET activo = :activo WHERE id = :id")
    suspend fun setActivo(id: Int, activo: Boolean)
}
