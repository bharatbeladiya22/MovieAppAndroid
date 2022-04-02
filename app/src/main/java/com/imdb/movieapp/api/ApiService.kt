package com.imdb.movieapp.api

import com.imdb.movieapp.data.ResponseDetails
import com.imdb.movieapp.data.ResponseMovieList
import com.imdb.movieapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.POPULAR_MOVIE)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<ResponseMovieList>


    @GET(Constants.DETAILS_MOVIE)
    suspend fun getMoviesDetails(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String
    ): Response<ResponseDetails>

}