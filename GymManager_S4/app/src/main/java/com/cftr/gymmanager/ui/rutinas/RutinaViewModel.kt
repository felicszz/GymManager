package com.cftr.gymmanager.ui.rutinas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cftr.gymmanager.data.db.EjercicioEntity
import com.cftr.gymmanager.data.db.RutinaEntity
import com.cftr.gymmanager.data.repository.RutinaRepository
import kotlinx.coroutines.launch

class RutinaViewModel(private val repository: RutinaRepository) : ViewModel() {

    val rutinas = repository.getAllRutinas().asLiveData()

    fun getEjerciciosByRutina(rutinaId: Int) = repository.getEjerciciosByRutina(rutinaId).asLiveData()

    fun insertarRutina(nombre: String, descripcion: String, socioId: Int = 0) {
        viewModelScope.launch {
            repository.insertRutina(RutinaEntity(nombre = nombre, descripcion = descripcion, socioId = socioId))
        }
    }

    fun actualizarRutina(rutina: RutinaEntity, nombre: String, descripcion: String) {
        viewModelScope.launch {
            repository.updateRutina(rutina.copy(nombre = nombre, descripcion = descripcion))
        }
    }

    fun eliminarRutina(rutina: RutinaEntity) {
        viewModelScope.launch { repository.deleteRutina(rutina) }
    }

    fun insertarEjercicio(rutinaId: Int, nombre: String, series: Int, repeticiones: Int, pesoKg: Double, notas: String) {
        viewModelScope.launch {
            repository.insertEjercicio(
                EjercicioEntity(
                    rutinaId = rutinaId,
                    nombre = nombre,
                    series = series,
                    repeticiones = repeticiones,
                    pesoKg = pesoKg,
                    notas = notas
                )
            )
        }
    }

    fun eliminarEjercicio(ejercicio: EjercicioEntity) {
        viewModelScope.launch { repository.deleteEjercicio(ejercicio) }
    }

    class Factory(private val repository: RutinaRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return RutinaViewModel(repository) as T
        }
    }
}
