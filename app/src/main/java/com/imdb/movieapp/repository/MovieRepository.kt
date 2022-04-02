package com.imdb.movieapp.repository

import com.imdb.movieapp.BuildConfig
import com.imdb.movieapp.api.ApiHelper
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getPopularMovies(page: Int) =
        apiHelper.getPopularMovies(apiKey = BuildConfig.TMDB_ApiKey, page = page)

    suspend fun getDetails(id: Long) =
        apiHelper.getDetails(id = id, apiKey = BuildConfig.TMDB_ApiKey)
}