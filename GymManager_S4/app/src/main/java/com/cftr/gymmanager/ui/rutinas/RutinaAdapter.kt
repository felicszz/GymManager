package com.cftr.gymmanager.ui.rutinas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cftr.gymmanager.data.db.RutinaEntity
import com.cftr.gymmanager.databinding.ItemRutinaBinding

class RutinaAdapter(
    private val onEdit: (RutinaEntity) -> Unit,
    private val onDelete: (RutinaEntity) -> Unit,
    private val onVerEjercicios: (RutinaEntity) -> Unit
) : ListAdapter<RutinaEntity, RutinaAdapter.RutinaViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<RutinaEntity>() {
        override fun areItemsTheSame(old: RutinaEntity, new: RutinaEntity) = old.id == new.id
        override fun areContentsTheSame(old: RutinaEntity, new: RutinaEntity) = old == new
    }

    inner class RutinaViewHolder(private val binding: ItemRutinaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(rutina: RutinaEntity) {
            binding.tvNombreRutina.text = rutina.nombre
            binding.tvDescripcionRutina.text = rutina.descripcion
            binding.btnEditarRutina.setOnClickListener { onEdit(rutina) }
            binding.btnEliminarRutina.setOnClickListener { onDelete(rutina) }
            binding.btnVerEjercicios.setOnClickListener { onVerEjercicios(rutina) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutinaViewHolder {
        val binding = ItemRutinaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RutinaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RutinaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
