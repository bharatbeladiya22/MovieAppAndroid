package com.imdb.movieapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.imdb.movieapp.R
import com.imdb.movieapp.di.GlideApp
import com.imdb.movieapp.utils.Constants.IMAGE_URL


class CustomBindingAdapter {
    companion object {

        @BindingAdapter(value = ["app:imageUrl"], requireAll = false)
        @JvmStatic
        fun imageUrl(imageView: ImageView, url: String?) {
            GlideApp.with(imageView).load(IMAGE_URL + url).placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder).into(imageView)
        }
    }
}