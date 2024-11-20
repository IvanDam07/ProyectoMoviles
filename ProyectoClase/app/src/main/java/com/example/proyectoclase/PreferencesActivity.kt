package com.example.proyectoclase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat

/**
 * Activity para gestionar las preferencias de la aplicación
 */
class PreferencesActivity : AppCompatActivity() {
    /**
     * Inicializa la actividad y carga el fragmento de preferencias
     *
     * @param savedInstanceState Guarda el estado previo de la actividad, si existe
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, SettingsFragment())
            .commit()
    }
}

/**
 * Fragmento que gestiona las preferencias de usuario
 */
class SettingsFragment : PreferenceFragmentCompat() {
    /**
     * Carga las preferencias
     *
     * @param savedInstanceState Estado guardado previo del fragmento, si existe
     * @param rootKey Clave raíz para las preferencias
     */
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}
