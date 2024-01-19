package com.example.recyclerviewpeliculas

import android.util.Log
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.awaitResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieAPI {

    private val apiKey = "3c1118671c82dcb670201bee008e026c"

    suspend fun makeApiCall(page: Int): List<Movie>? = withContext(Dispatchers.IO) {
        try {
            // Update the URL to include the page parameter
            val call: Call<MovieList> = RetrofitInstance.movieApiService.getMovieDetails(apiKey, page)
            val response = call.awaitResponse()

            if (response.isSuccessful) {
                val movieList: MovieList? = response.body()
                return@withContext movieList?.movies
            } else {
                // Handle unsuccessful response
                Log.e("Hola", "Unsuccessful response: ${response.code()}")
            }
        } catch (e: Exception) {
            // Handle exception
            Log.e("Hola", "Error during API call: ${e.message}", e)
        }
        return@withContext null
    }
}

interface ApiService {
    @GET("movie/top_rated?language=en-US")
    fun getMovieDetails(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<MovieList>
}

data class MovieList(
    @SerializedName("results")
    val movies: List<Movie>,
)

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("poster_path")
    val imageUrl: String
    // Add other attributes as needed
)

object RetrofitInstance {
    private const val APIURL = "https://api.themoviedb.org/3/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(APIURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieApiService: ApiService = retrofit.create(ApiService::class.java)
}