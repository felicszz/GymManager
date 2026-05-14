package com.cftr.gymmanager.data.repository

import com.cftr.gymmanager.data.dao.EjercicioDao
import com.cftr.gymmanager.data.dao.RutinaDao
import com.cftr.gymmanager.data.db.EjercicioEntity
import com.cftr.gymmanager.data.db.RutinaEntity
import kotlinx.coroutines.flow.Flow

class RutinaRepository(
    private val rutinaDao: RutinaDao,
    private val ejercicioDao: EjercicioDao
) {
    fun getAllRutinas(): Flow<List<RutinaEntity>> = rutinaDao.getAllRutinas()
    fun getRutinasBySocio(socioId: Int): Flow<List<RutinaEntity>> = rutinaDao.getRutinasBySocio(socioId)
    fun getEjerciciosByRutina(rutinaId: Int): Flow<List<EjercicioEntity>> = ejercicioDao.getEjerciciosByRutina(rutinaId)

    suspend fun insertRutina(rutina: RutinaEntity): Long = rutinaDao.insertRutina(rutina)
    suspend fun updateRutina(rutina: RutinaEntity) = rutinaDao.updateRutina(rutina)
    suspend fun deleteRutina(rutina: RutinaEntity) = rutinaDao.deleteRutina(rutina)

    suspend fun insertEjercicio(ejercicio: EjercicioEntity): Long = ejercicioDao.insertEjercicio(ejercicio)
    suspend fun updateEjercicio(ejercicio: EjercicioEntity) = ejercicioDao.updateEjercicio(ejercicio)
    suspend fun deleteEjercicio(ejercicio: EjercicioEntity) = ejercicioDao.deleteEjercicio(ejercicio)
}
