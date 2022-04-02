package com.imdb.movieapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.imdb.movieapp.data.datasource.MovieDataSource
import com.imdb.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val movieData = Pager(PagingConfig(pageSize = 20)) {
        MovieDataSource(movieRepository = movieRepository)
    }.flow.cachedIn(scope = viewModelScope)

}