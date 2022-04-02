package com.imdb.movieapp.data

import com.google.gson.annotations.SerializedName

data class ResponseMovieList(
    @field:SerializedName("results")
    val data: List<Movie>? = null,
)



