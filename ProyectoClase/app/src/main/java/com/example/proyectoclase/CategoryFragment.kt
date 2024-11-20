package com.example.proyectoclase

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.proyectoclase.databinding.FragmentCategoryBinding
import com.example.proyectoclase.R

/**
 * Fragment que muestra una lista de categorías y permite seleccionar una
 */
class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding

    /**
     * Crea la vista del fragmento.
     *
     * @param inflater Inflador para las vistas
     * @param container Contenedor padre de la vista
     * @param savedInstanceState Estado guardado previo del fragmento, si existe
     * @return Vista creada
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Configura la lógica después de que la vista es creada
     *
     * @param view Vista creada
     * @param savedInstanceState Estado guardado previo del fragmento, si existe
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = listOf(
            getString(R.string.all),
            getString(R.string.sports),
            getString(R.string.technology),
            getString(R.string.videogames),
            getString(R.string.cinema),
            getString(R.string.science)
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, categories)
        binding.categoryListView.adapter = adapter

        binding.categoryListView.setOnItemClickListener { _, _, position, _ ->
            val category = categories[position]
            val intent = Intent(requireContext(), CategoryActivity::class.java)
            intent.putExtra("CATEGORY", category)
            startActivity(intent)
        }
    }
}