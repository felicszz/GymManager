package com.cftr.gymmanager.ui.socios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cftr.gymmanager.GymApplication
import com.cftr.gymmanager.data.db.SocioEntity
import com.cftr.gymmanager.databinding.DialogSocioBinding
import com.cftr.gymmanager.databinding.FragmentSociosBinding

class SociosFragment : Fragment() {

    private var _binding: FragmentSociosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SocioViewModel by viewModels {
        SocioViewModel.Factory((requireActivity().application as GymApplication).socioRepository)
    }

    private lateinit var adapter: SocioAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSociosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SocioAdapter(
            onEdit = { mostrarDialogo(it) },
            onDelete = { confirmarEliminar(it) },
            onToggleActivo = { viewModel.toggleActivo(it) }
        )

        binding.recyclerSocios.adapter = adapter

        viewModel.socios.observe(viewLifecycleOwner) { lista ->
            adapter.submitList(lista)
            binding.tvVacio.visibility = if (lista.isEmpty()) View.VISIBLE else View.GONE
        }

        binding.fabNuevoSocio.setOnClickListener { mostrarDialogo(null) }
    }

    private fun mostrarDialogo(socio: SocioEntity?) {
        val dialogBinding = DialogSocioBinding.inflate(layoutInflater)

        socio?.let {
            dialogBinding.etNombre.setText(it.nombre)
            dialogBinding.etApellidos.setText(it.apellidos)
            dialogBinding.etEmail.setText(it.email)
            dialogBinding.etTelefono.setText(it.telefono)
        }

        AlertDialog.Builder(requireContext())
            .setTitle(if (socio == null) "Nuevo socio" else "Editar socio")
            .setView(dialogBinding.root)
            .setPositiveButton("Guardar") { _, _ ->
                val nombre = dialogBinding.etNombre.text.toString().trim()
                val apellidos = dialogBinding.etApellidos.text.toString().trim()
                val email = dialogBinding.etEmail.text.toString().trim()
                val telefono = dialogBinding.etTelefono.text.toString().trim()

                if (nombre.isBlank() || apellidos.isBlank()) return@setPositiveButton

                if (socio == null) {
                    viewModel.insertar(nombre, apellidos, email, telefono)
                } else {
                    viewModel.actualizar(socio, nombre, apellidos, email, telefono)
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun confirmarEliminar(socio: SocioEntity) {
        AlertDialog.Builder(requireContext())
            .setTitle("Eliminar socio")
            .setMessage("¿Seguro que quieres eliminar a ${socio.nombre} ${socio.apellidos}?")
            .setPositiveButton("Eliminar") { _, _ -> viewModel.eliminar(socio) }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
