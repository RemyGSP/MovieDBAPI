package com.example.recyclerviewpeliculas

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.squareup.picasso.Picasso
import coil.load
import java.io.Serializable

class PeliculasAdapter(private val FilmList : List<Movie>) : RecyclerView.Adapter<PeliculasAdapter.ViewHolder>() {

    var allMovies: MutableList<Movie> = FilmList.toMutableList()

    fun addData(newMovies: List<Movie>) {
        val startPosition = allMovies.size
        allMovies.addAll(newMovies)
        Log.d("Movies23",allMovies.toString())
        notifyItemRangeInserted(startPosition, newMovies.size)
        notifyDataSetChanged()

    }



    inner class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.tituloPelicula)
        var image: ImageView = itemView.findViewById(R.id.imagenPelicula)
        var ratings: TextView = itemView.findViewById(R.id.ratingsTextView)

        init {
            itemView.setOnClickListener {
                // Handle item click here
                val position = adapterPosition
                    val clickedMovie = allMovies[position]

                    val newScreen = Intent(itemView.context, DetallesPelicula::class.java)
                    newScreen.putExtra("clickedMovie", clickedMovie as Serializable)


                itemView.context.startActivity(newScreen)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context);
        val filmView = inflater.inflate(R.layout.layoutrecyclerview,parent,false);
        return ViewHolder(filmView)
    }

    override fun getItemCount(): Int {
        return allMovies!!.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film: Movie = allMovies!!.get(position)
        val textView =holder.titleTextView;
        textView.text = film.title;
        val ratings = holder.ratings
        ratings.text = film.voteAverage.toString();
        Picasso.get().load("https://image.tmdb.org/t/p/w500/${film.imageUrl}")
            .placeholder(R.drawable.film_placeholder)
            .into(holder.image)

        Log.d("Position", position.toString());
    }


}