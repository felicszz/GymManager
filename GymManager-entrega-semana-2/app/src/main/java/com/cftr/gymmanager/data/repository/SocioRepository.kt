package com.cftr.gymmanager.data.repository

import com.cftr.gymmanager.data.dao.SocioDao
import com.cftr.gymmanager.data.db.SocioEntity
import kotlinx.coroutines.flow.Flow

class SocioRepository(private val socioDao: SocioDao) {

    fun getAllSocios(): Flow<List<SocioEntity>> = socioDao.getAllSocios()

    fun getSociosActivos(): Flow<List<SocioEntity>> = socioDao.getSociosActivos()

    fun buscarSocios(query: String): Flow<List<SocioEntity>> = socioDao.buscarSocios(query)

    suspend fun getSocioById(id: Int): SocioEntity? = socioDao.getSocioById(id)

    suspend fun insertSocio(socio: SocioEntity): Long = socioDao.insertSocio(socio)

    suspend fun updateSocio(socio: SocioEntity) = socioDao.updateSocio(socio)

    suspend fun deleteSocio(socio: SocioEntity) = socioDao.deleteSocio(socio)

    suspend fun setActivo(id: Int, activo: Boolean) = socioDao.setActivo(id, activo)
}
