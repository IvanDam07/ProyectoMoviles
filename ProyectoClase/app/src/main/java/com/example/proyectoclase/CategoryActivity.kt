package com.example.proyectoclase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoclase.databinding.ActivityCategoryBinding

/**
 * Activity que muestra una lista de noticias filtradas por categoría
 */
class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding

    /**
     * Inicializa la actividad y carga el fragmento de categorías
     *
     * @param savedInstanceState Estado guardado previo de la actividad, si existe
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getStringExtra("CATEGORY") ?: getString(R.string.all)
        binding.categoryTitleTextView.text = getString(R.string.news_of, category)

        val news = getNoticiasPorCategoria(category)
        val adapter = NewsAdapter(news)
        binding.newsRecyclerView.adapter = adapter
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    /**
     * Obtiene una lista de noticias filtradas por categoría
     *
     * @param category Nombre de la categoría seleccionada
     * @return Lista de noticias correspondientes a la categoría
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