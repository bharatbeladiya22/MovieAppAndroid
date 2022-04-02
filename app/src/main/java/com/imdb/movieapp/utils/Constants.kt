package com.imdb.movieapp.utils

object Constants {

    const val BASE_URL: String = "https://api.themoviedb.org/3/"

    //API
    const val POPULAR_MOVIE = "movie/popular"
    const val DETAILS_MOVIE = "movie/{id}?append_to_response=videos,runtime"

    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    const val YOUTUBE_WEB_URL = "https://www.youtube.com/watch?v="
    const val IMAGE_SIZE_W185 = "w185"
    const val IMAGE_URL = IMAGE_BASE_URL + IMAGE_SIZE_W185


}