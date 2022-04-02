package com.imdb.movieapp.data

import com.google.gson.annotations.SerializedName

data class ResponseDetails(
    @field:SerializedName("runtime")
    val runTime: Int? = null,
    @SerializedName("videos")
    var videos: Videos? = null
) {
    fun getRuntimeString(): String {
        return if (runTime != null) "$runTime mins" else ""
    }
}

data class Videos(
    @SerializedName("results")
    var trailers: List<Trailer>? = null
)

data class Trailer(

    @SerializedName("id")
    var id: String,

    @SerializedName("key")
    var key: String? = null,

    @SerializedName("site")
    var site: String? = null,

    @SerializedName("name")
    var title: String? = null
)



