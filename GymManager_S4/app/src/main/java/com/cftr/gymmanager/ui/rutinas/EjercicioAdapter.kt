package com.cftr.gymmanager.ui.rutinas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cftr.gymmanager.data.db.EjercicioEntity
import com.cftr.gymmanager.databinding.ItemEjercicioBinding

class EjercicioAdapter(
    private val onDelete: (EjercicioEntity) -> Unit
) : ListAdapter<EjercicioEntity, EjercicioAdapter.EjercicioViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<EjercicioEntity>() {
        override fun areItemsTheSame(old: EjercicioEntity, new: EjercicioEntity) = old.id == new.id
        override fun areContentsTheSame(old: EjercicioEntity, new: EjercicioEntity) = old == new
    }

    inner class EjercicioViewHolder(private val binding: ItemEjercicioBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ejercicio: EjercicioEntity) {
            binding.tvNombreEjercicio.text = ejercicio.nombre
            binding.tvSeriesReps.text = "${ejercicio.series} series x ${ejercicio.repeticiones} reps"
            binding.tvPeso.text = if (ejercicio.pesoKg > 0) "${ejercicio.pesoKg} kg" else "Sin peso"
            binding.tvNotas.text = ejercicio.notas.ifBlank { "" }
            binding.btnEliminarEjercicio.setOnClickListener { onDelete(ejercicio) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioViewHolder {
        val binding = ItemEjercicioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EjercicioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EjercicioViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
