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
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}