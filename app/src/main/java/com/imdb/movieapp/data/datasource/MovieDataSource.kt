package com.imdb.movieapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imdb.movieapp.data.Movie
import com.imdb.movieapp.repository.MovieRepository
import org.json.JSONObject


class MovieDataSource(private val movieRepository: MovieRepository) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = movieRepository.getPopularMovies(currentLoadingPageKey)
            val responseData = mutableListOf<Movie>()
            if (response.isSuccessful) {
                val data = response.body()?.data ?: emptyList()
                responseData.addAll(data)
            } else {
                val jsonData = JSONObject(response.errorBody()!!.string())
                return LoadResult.Error(Throwable(jsonData.optString("status_message")))
            }
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return when (e) {
                is retrofit2.HttpException -> {
                    when (e.code()) {
                        400 -> LoadResult.Error(Throwable("Bad Request: The server could not understand the request due to invalid syntax."))
                        401 -> LoadResult.Error(Throwable("Unauthorized: Invalid API key or authentication credentials."))
                        403 -> LoadResult.Error(Throwable("Forbidden: You do not have permission to access this resource."))
                        404 -> LoadResult.Error(Throwable("Not Found: The requested resource could not be found."))
                        429 -> LoadResult.Error(Throwable("Too Many Requests: You have exceeded the rate limit."))
                        500 -> LoadResult.Error(Throwable("Internal Server Error: Something went wrong on the server."))
                        502 -> LoadResult.Error(Throwable("Bad Gateway: The server received an invalid response from an upstream server."))
                        503 -> LoadResult.Error(Throwable("Service Unavailable: The server is temporarily unable to handle the request."))
                        504 -> LoadResult.Error(Throwable("Gateway Timeout: The server did not receive a timely response from an upstream server."))
                        else -> LoadResult.Error(Throwable("HTTP error ${e.code()}: ${e.message()}"))
                    }
                }
                is java.io.IOException -> LoadResult.Error(Throwable("Network error: Please check your internet connection and try again."))
                is SecurityException -> LoadResult.Error(Throwable("Security error: Permission denied."))
                else -> LoadResult.Error(Throwable("An unexpected error occurred: ${e.localizedMessage}"))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}