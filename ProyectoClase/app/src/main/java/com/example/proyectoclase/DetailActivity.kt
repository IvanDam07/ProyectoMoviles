package com.example.proyectoclase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoclase.databinding.ActivityDetailBinding

/**
 * Detalle de una noticia o elemento. Muestra el título y la descripción recibidos en el intent
 */
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    /**
     * Inicializa la actividad y muestra el contenido recibido
     *
     * @param savedInstanceState Estado guardado previo de la actividad, si existe
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Obtiene los datos de la noticia desde el intent
        val title = intent.getStringExtra("TITLE") ?: ""
        val description = intent.getStringExtra("DESCRIPTION") ?: ""

        //Muestra los datos de la interfaz de usuario
        binding.titleTextView.text = title
        binding.descriptionTextView.text = description
    }
}