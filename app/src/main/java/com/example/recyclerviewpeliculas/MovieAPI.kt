package com.example.recyclerviewpeliculas
import android.util.Log
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class MovieAPI {
    val imageSrc : String = "";
    public fun makeApiCall(): List<Movie>? {
        var movieList1 : List<Movie> = listOf();
        val call: Call<MovieList> = RetrofitInstance.movieApiService.getMovieDetails(APIKEY)
        call.enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.isSuccessful) {
                    val movieList  : MovieList? = response.body();
                    if (movieList != null) {
                        movieList1 = movieList.movies;
                        Log.d("movies", movieList1.toString())
                        Log.d("Movies2", movieList.movies.toString())
                    }
                } else {
                }
            }
            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                // Handle failure
            }

        })
        Log.d("movie3", movieList1.toString())
        return movieList1 ;
        }
}

const val APIKEY = "3c1118671c82dcb670201bee008e026c"
const val apiUrl = "https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1";
interface ApiService {
    @GET("movie/top_rated?language=en-US&page=1")
    fun getMovieDetails(
        @Query("api_key") apiKey: String
    ): Call<MovieList>
}

public data class MovieList(
    @SerializedName("results")
    public val movies: List<Movie>,
){

}




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

object RetrofitInstance{
    private const val APIURL = "https://api.themoviedb.org/3/"
    private const val APIKEY = "3c1118671c82dcb670201bee008e026c"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(APIURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieApiService: ApiService = retrofit.create(ApiService::class.java)
}


