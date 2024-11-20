package com.example.proyectoclase

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoclase.databinding.NewsItemBinding

/**
 * Adaptador para mostrar noticias en un RecyclerView.
 *
 * @param newsList Lista de noticias a mostrar.
 */
class NewsAdapter(private val newsList: List<Noticias>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    /**
     * Clase interna que representa un elemento de la lista de noticias
     *
     * @param binding Enlace a los componentes de un item de la lista
     */
    class NewsViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        //Título de la noticia
        val titleTextView: TextView = binding.newsTitleTextView
        //Descripción de la noticia
        val descriptionTextView: TextView = binding.newsDescriptionTextView
    }

    /**
     * Crea un nuevo ViewHolder para un elemento de la lista.
     *
     * @param parent Vista padre que contendrá el ViewHolder
     * @param viewType Tipo de vista del elemento
     * @return Nuevo ViewHolder creado
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    /**
     * Vincula un elemento de la lista a su ViewHolder.
     *
     * @param holder ViewHolder que se está configurando
     * @param position Posición de la noticia en la lista.
     */
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.titleTextView.text = news.titulo
        holder.descriptionTextView.text = news.descripcion

        //Establece un clic para mostrar en detalle la noticia
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("TITLE", news.titulo)
            intent.putExtra("DESCRIPTION", news.descripcion)
            holder.itemView.context.startActivity(intent)
        }
    }

    /**
     * Obtiene el número de elementos en la lista.
     *
     * @return Número de elementos en la lista.
     */
    override fun getItemCount() = newsList.size
}