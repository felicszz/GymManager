package com.cftr.gymmanager.ui.rutinas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cftr.gymmanager.GymApplication
import com.cftr.gymmanager.data.db.RutinaEntity
import com.cftr.gymmanager.databinding.DialogEjercicioBinding
import com.cftr.gymmanager.databinding.DialogRutinaBinding
import com.cftr.gymmanager.databinding.FragmentRutinasBinding

class RutinasFragment : Fragment() {

    private var _binding: FragmentRutinasBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RutinaViewModel by viewModels {
        RutinaViewModel.Factory((requireActivity().application as GymApplication).rutinaRepository)
    }

    private lateinit var rutinaAdapter: RutinaAdapter
    private var rutinaSeleccionada: RutinaEntity? = null
    private lateinit var ejercicioAdapter: EjercicioAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRutinasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ejercicioAdapter = EjercicioAdapter(onDelete = { viewModel.eliminarEjercicio(it) })

        rutinaAdapter = RutinaAdapter(
            onEdit = { mostrarDialogoRutina(it) },
            onDelete = { confirmarEliminarRutina(it) },
            onVerEjercicios = { rutina ->
                rutinaSeleccionada = rutina
                binding.layoutEjercicios.visibility = View.VISIBLE
                binding.tvTituloEjercicios.text = "Ejercicios: ${rutina.nombre}"
                binding.recyclerEjercicios.adapter = ejercicioAdapter
                viewModel.getEjerciciosByRutina(rutina.id).observe(viewLifecycleOwner) {
                    ejercicioAdapter.submitList(it)
                }
            }
        )

        binding.recyclerRutinas.adapter = rutinaAdapter
        viewModel.rutinas.observe(viewLifecycleOwner) { lista ->
            rutinaAdapter.submitList(lista)
            binding.tvVacioRutinas.visibility = if (lista.isEmpty()) View.VISIBLE else View.GONE
        }

        binding.fabNuevaRutina.setOnClickListener { mostrarDialogoRutina(null) }
        binding.fabNuevoEjercicio.setOnClickListener { rutinaSeleccionada?.let { mostrarDialogoEjercicio(it) } }
    }

    private fun mostrarDialogoRutina(rutina: RutinaEntity?) {
        val d = DialogRutinaBinding.inflate(layoutInflater)
        rutina?.let { d.etNombreRutina.setText(it.nombre); d.etDescripcionRutina.setText(it.descripcion) }
        AlertDialog.Builder(requireContext())
            .setTitle(if (rutina == null) "Nueva rutina" else "Editar rutina")
            .setView(d.root)
            .setPositiveButton("Guardar") { _, _ ->
                val nombre = d.etNombreRutina.text.toString().trim()
                val desc = d.etDescripcionRutina.text.toString().trim()
                if (nombre.isBlank()) return@setPositiveButton
                if (rutina == null) viewModel.insertarRutina(nombre, desc)
                else viewModel.actualizarRutina(rutina, nombre, desc)
            }
            .setNegativeButton("Cancelar", null).show()
    }

    private fun mostrarDialogoEjercicio(rutina: RutinaEntity) {
        val d = DialogEjercicioBinding.inflate(layoutInflater)
        AlertDialog.Builder(requireContext())
            .setTitle("Nuevo ejercicio — ${rutina.nombre}")
            .setView(d.root)
            .setPositiveButton("Añadir") { _, _ ->
                val nombre = d.etNombreEjercicio.text.toString().trim()
                val series = d.etSeries.text.toString().toIntOrNull() ?: 3
                val reps = d.etRepeticiones.text.toString().toIntOrNull() ?: 10
                val peso = d.etPeso.text.toString().toDoubleOrNull() ?: 0.0
                val notas = d.etNotasEjercicio.text.toString().trim()
                if (nombre.isBlank()) return@setPositiveButton
                viewModel.insertarEjercicio(rutina.id, nombre, series, reps, peso, notas)
            }
            .setNegativeButton("Cancelar", null).show()
    }

    private fun confirmarEliminarRutina(rutina: RutinaEntity) {
        AlertDialog.Builder(requireContext())
            .setTitle("Eliminar rutina")
            .setMessage("¿Eliminar '${rutina.nombre}' y todos sus ejercicios?")
            .setPositiveButton("Eliminar") { _, _ -> viewModel.eliminarRutina(rutina) }
            .setNegativeButton("Cancelar", null).show()
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
