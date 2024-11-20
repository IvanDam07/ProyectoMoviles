package com.example.proyectoclase

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity que muestra información sobre la aplicación
 */
class AboutActivity : AppCompatActivity() {
    /**
     * Inicializa la actividad y establece el texto informativo
     *
     * @param savedInstanceState Estado guardado previo de la actividad, si existe
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // Inicializa el TextView y establece el texto desde los recursos.
        val aboutTextView: TextView = findViewById(R.id.aboutTextView)
        aboutTextView.text = getString(R.string.about_text)
    }
}