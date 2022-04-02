package com.imdb.movieapp.api

import com.imdb.movieapp.data.ResponseDetails
import com.imdb.movieapp.data.ResponseMovieList
import retrofit2.Response

interface ApiHelper {
    suspend fun getPopularMovies(apiKey: String, page: Int): Response<ResponseMovieList>
    suspend fun getDetails(apiKey: String,id: Long): Response<ResponseDetails>
}