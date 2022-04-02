package com.imdb.movieapp.api

import com.imdb.movieapp.data.ResponseDetails
import com.imdb.movieapp.data.ResponseMovieList
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun getPopularMovies(apiKey: String, page: Int): Response<ResponseMovieList> =
        apiService.getPopularMovies(apiKey, page)

    override suspend fun getDetails(apiKey: String, id: Long): Response<ResponseDetails> =
        apiService.getMoviesDetails(apiKey = apiKey, id = id)
}