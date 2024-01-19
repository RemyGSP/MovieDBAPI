package com.example.recyclerviewpeliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var peliculasAdapter: PeliculasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView and set layout manager
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPeliculas)
        recyclerView.layoutManager = GridLayoutManager(this,2)

        // Create an empty list of movies initially

        // Create adapter and set it to your RecyclerView


        // Call the Movie API and update the adapter when the data is received
        val movieAPI = MovieAPI()

        val movies : List<Movie>? = movieAPI.makeApiCall();
        peliculasAdapter = PeliculasAdapter(movies)
        Log.d("NotAmogus",movies.toString())
        recyclerView.adapter = peliculasAdapter

    }
}