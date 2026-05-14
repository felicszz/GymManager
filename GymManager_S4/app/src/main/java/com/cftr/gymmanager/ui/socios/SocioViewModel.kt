package com.cftr.gymmanager.ui.socios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cftr.gymmanager.data.db.SocioEntity
import com.cftr.gymmanager.data.repository.SocioRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SocioViewModel(private val repository: SocioRepository) : ViewModel() {

    val socios = repository.getAllSocios().asLiveData()

    fun insertar(nombre: String, apellidos: String, email: String, telefono: String) {
        val fecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        viewModelScope.launch {
            repository.insertSocio(
                SocioEntity(
                    nombre = nombre,
                    apellidos = apellidos,
                    email = email,
                    telefono = telefono,
                    fechaAlta = fecha
                )
            )
        }
    }

    fun actualizar(socio: SocioEntity, nombre: String, apellidos: String, email: String, telefono: String) {
        viewModelScope.launch {
            repository.updateSocio(socio.copy(nombre = nombre, apellidos = apellidos, email = email, telefono = telefono))
        }
    }

    fun eliminar(socio: SocioEntity) {
        viewModelScope.launch { repository.deleteSocio(socio) }
    }

    fun toggleActivo(socio: SocioEntity) {
        viewModelScope.launch { repository.setActivo(socio.id, !socio.activo) }
    }

    class Factory(private val repository: SocioRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return SocioViewModel(repository) as T
        }
    }
}
