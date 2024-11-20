package com.example.proyectoclase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoclase.databinding.FragmentNewsBinding

/**
 * Fragmento que muestra noticias y permite filtrarlas por categorías.
 */
class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var categorySpinner: Spinner

    /**
     * Crea la vista del fragmento
     *
     * @param inflater Inflador para las vistas
     * @param container Contenedor de la vista
     * @param savedInstanceState Estado guardado previo del fragmento, si existe
     * @return Vista creada
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Configura los elementos de la vista una vez creada
     *
     * @param view Vista creada
     * @param savedInstanceState Estado guardado previo del fragmento, si existe
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categorySpinner = binding.categorySpinner

        val categorias = arrayOf(
            getString(R.string.all),
            getString(R.string.sports),
            getString(R.string.technology),
            getString(R.string.videogames),
            getString(R.string.cinema),
            getString(R.string.science)
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categorias)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        categorySpinner.adapter = adapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val categoriaSeleccionada = parent.getItemAtPosition(position).toString()
                actualizacionNoticias(categoriaSeleccionada)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No se necesita implementación
            }
        }
    }

    /**
     * Actualiza las noticias según la categoría seleccionada
     *
     * @param category Categoría seleccionada
     */
    private fun actualizacionNoticias(category: String) {
        val nuevaLista = getNoticiasPorCategoria(category)

        binding.sectionTitleTextView.text = if (category == getString(R.string.all))
            getString(R.string.main_news)
        else
            getString(R.string.news_of, category)

        val newsAdapter = NewsAdapter(nuevaLista)
        binding.newsRecyclerView.adapter = newsAdapter
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    /**
     * Obtiene una lista de noticias filtradas por categoría
     *
     * @param category Categoría seleccionada
     * @return Lista de noticias filtradas
     */
    private fun getNoticiasPorCategoria(category: String): List<Noticias> {
        return when (category) {
            getString(R.string.all) -> listOf(
                Noticias(getString(R.string.important_news_1), getString(R.string.description_news_1)),
                Noticias(getString(R.string.important_news_2), getString(R.string.description_news_2)),
                Noticias(getString(R.string.important_news_3), getString(R.string.description_news_3))
            )
            getString(R.string.sports) -> listOf(
                Noticias(getString(R.string.sports_news_1), getString(R.string.sports_description_1)),
                Noticias(getString(R.string.sports_news_2), getString(R.string.sports_description_2))
            )
            getString(R.string.technology) -> listOf(
                Noticias(getString(R.string.tech_news_1), getString(R.string.tech_description_1)),
                Noticias(getString(R.string.tech_news_2), getString(R.string.tech_description_2))
            )
            getString(R.string.videogames) -> listOf(
                Noticias(getString(R.string.games_news_1), getString(R.string.games_description_1)),
                Noticias(getString(R.string.games_news_2), getString(R.string.games_description_2))
            )
            getString(R.string.science) -> listOf(
                Noticias(getString(R.string.science_news_1), getString(R.string.science_description_1)),
                Noticias(getString(R.string.science_news_2), getString(R.string.science_description_2))
            )
            getString(R.string.cinema) -> listOf(
                Noticias(getString(R.string.cinema_news_1), getString(R.string.cinema_description_1)),
                Noticias(getString(R.string.cinema_news_2), getString(R.string.cinema_description_2))
            )
            else -> emptyList()
        }
    }
}

/**
 * Modelo de datos que representa una noticia
 *
 * @param titulo Título de la noticia
 * @param descripcion Descripción de la noticia
 */
data class Noticias(val titulo: String, val descripcion: String)