package com.imdb.movieapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    @SerializedName("id")
    var id: Long = 0,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("vote_average")
    var voteAverage: Double = 0.toDouble(),

    @SerializedName("release_date")
    var releaseDate: String? = null
) : Serializable


