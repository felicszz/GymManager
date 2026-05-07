package com.cftr.gymmanager.data.dao

import androidx.room.*
import com.cftr.gymmanager.data.db.CuotaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CuotaDao {

    @Query("SELECT * FROM cuotas ORDER BY mes DESC")
    fun getAllCuotas(): Flow<List<CuotaEntity>>

    @Query("SELECT * FROM cuotas WHERE socioId = :socioId ORDER BY mes DESC")
    fun getCuotasBySocio(socioId: Int): Flow<List<CuotaEntity>>

    @Query("SELECT * FROM cuotas WHERE pagada = 0 ORDER BY mes ASC")
    fun getCuotasPendientes(): Flow<List<CuotaEntity>>

    @Query("SELECT * FROM cuotas WHERE id = :id")
    suspend fun getCuotaById(id: Int): CuotaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCuota(cuota: CuotaEntity): Long

    @Update
    suspend fun updateCuota(cuota: CuotaEntity)

    @Delete
    suspend fun deleteCuota(cuota: CuotaEntity)

    @Query("UPDATE cuotas SET pagada = 1, fechaPago = :fecha WHERE id = :id")
    suspend fun marcarComoPagada(id: Int, fecha: String)
}
