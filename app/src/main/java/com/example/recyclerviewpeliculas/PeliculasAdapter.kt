package com.example.recyclerviewpeliculas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.squareup.picasso.Picasso
import coil.load

class PeliculasAdapter(private val FilmList: List<Movie>?) : RecyclerView.Adapter<PeliculasAdapter.ViewHolder>() {

    lateinit var context: Context;
    inner class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.tituloPelicula)
        var image: ImageView = itemView.findViewById(R.id.imagenPelicula)
    };

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent?.context;
        val inflater = LayoutInflater.from(context);
        val filmView = inflater.inflate(R.layout.layoutrecyclerview,parent,false);
        return ViewHolder(filmView)
    }

    override fun getItemCount(): Int {
        return FilmList!!.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film: Movie = FilmList!!.get(position)
        val textView =holder.titleTextView;
        textView.text = film.title;

        Picasso.get().load("https://image.tmdb.org/t/p/w500/${film.imageUrl}")
            .placeholder(R.drawable.film_placeholder)
            .into(holder.image)

    }


}