package com.imdb.movieapp.ui.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imdb.movieapp.data.ResponseDetails
import com.imdb.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    val details = MutableLiveData<ResponseDetails>()

    fun getMovieDetails(id: Long) {
        viewModelScope.launch {
            movieRepository.getDetails(id).let {
                if (it.isSuccessful) {
                    Log.d("details", it.toString())
                    details.postValue(it.body())
                }
            }
        }
    }

}