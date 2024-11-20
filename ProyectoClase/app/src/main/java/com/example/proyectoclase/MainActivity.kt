package com.example.proyectoclase

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.preference.PreferenceManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.proyectoclase.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Activity principal de la aplicación que gestiona las pestañas de noticias y categorías
 */
class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    // View binding para acceder fácilmente a los elementos de la vista
    private lateinit var binding: ActivityMainBinding

    // Elementos principales de la interfaz de usuario
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    /**
     * Inicializa la activity y configura el ViewPager con sus pestañas
     *
     * @param savedInstanceState Estado guardado previo de la actividad, si existe
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Infla el diseño utilizando View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa los componentes ViewPager y TabLayout desde el diseño
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout

        //Configura el ViewPager y el adaptador para las pestañas
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // Vincula TabLayout con ViewPager usando TabLayoutMediator
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> getString(R.string.news) //Título de la primera pestaña
                1 -> getString(R.string.categories) //Título de la segunda pestaña
                else -> getString(R.string.tab_default, position + 1) //Título por defecto
            }
        }.attach()

        // Registra el listener de preferencias para detectar cambios
        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)

        // Aplica el tema (claro u oscuro) al iniciar la aplicación según la preferencia guardada
        val isDarkMode = PreferenceManager.getDefaultSharedPreferences(this)
            .getBoolean("dark_mode", false)
        applyTheme(isDarkMode)
    }

    /**
     * Método para inflar el menú principal de la aplicación
     *
     * @param menu El menú a inflar
     * @return `true` si el menú se ha inflado correctamente
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /**
     * Maneja las acciones del menú
     *
     * @param item Elemento seleccionado del menú
     * @return `true` si la acción fue manejada correctamente
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_preferences -> {
                //Abre la activity de preferencias
                startActivity(Intent(this, PreferencesActivity::class.java))
                true
            }
            R.id.action_web -> {
                //Abre un navegador con una URL específica
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com"))
                startActivity(intent)
                true
            }
            R.id.action_about -> {
                //Abre la actividad Acerca de
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Aplica el tema de la aplicación según la preferencia de modo oscuro
     *
     * @param isDarkMode `true` para activar el modo oscuro, `false` para el modo claro
     */
    private fun applyTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    /**
     * Método llamado cuando se detecta un cambio en las preferencias compartidas
     *
     * @param sharedPreferences Preferencias compartidas de la aplicación
     * @param key Clave de la preferencia que ha cambiado
     */
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "dark_mode") {
            val isDarkMode = sharedPreferences?.getBoolean(key, false) ?: false
            applyTheme(isDarkMode)
        }
    }

    /**
     * Elimina el registro del listener de preferencias al destruir la actividad
     */
    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
    }
}

/**
 * Adaptador para gestionar las pestañas y los fragmentos asociados
 *
 * @param fragmentActivity Actividad contenedora del ViewPager2
 */
class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    /**
     * Número de pestañas en el ViewPager2
     */
    override fun getItemCount(): Int = 2

    /**
     * Crea el fragmento correspondiente a cada pestaña
     *
     * @param position Índice de la pestaña
     * @return El fragmento asociado a la pestaña
     */
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> NewsFragment() //Fragmento para noticias
            1 -> CategoryFragment() //Fragmento para categorias
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}