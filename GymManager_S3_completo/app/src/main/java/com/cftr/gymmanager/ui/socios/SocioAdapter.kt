package com.cftr.gymmanager.ui.socios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cftr.gymmanager.data.db.SocioEntity
import com.cftr.gymmanager.databinding.ItemSocioBinding

class SocioAdapter(
    private val onEdit: (SocioEntity) -> Unit,
    private val onDelete: (SocioEntity) -> Unit,
    private val onToggleActivo: (SocioEntity) -> Unit
) : ListAdapter<SocioEntity, SocioAdapter.SocioViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<SocioEntity>() {
        override fun areItemsTheSame(old: SocioEntity, new: SocioEntity) = old.id == new.id
        override fun areContentsTheSame(old: SocioEntity, new: SocioEntity) = old == new
    }

    inner class SocioViewHolder(private val binding: ItemSocioBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(socio: SocioEntity) {
            binding.tvNombre.text = "${socio.apellidos}, ${socio.nombre}"
            binding.tvEmail.text = socio.email
            binding.tvTelefono.text = socio.telefono
            binding.tvFechaAlta.text = "Alta: ${socio.fechaAlta}"
            binding.chipActivo.text = if (socio.activo) "Activo" else "Baja"
            binding.chipActivo.isChecked = socio.activo

            binding.btnEditar.setOnClickListener { onEdit(socio) }
            binding.btnEliminar.setOnClickListener { onDelete(socio) }
            binding.chipActivo.setOnClickListener { onToggleActivo(socio) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocioViewHolder {
        val binding = ItemSocioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SocioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SocioViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
