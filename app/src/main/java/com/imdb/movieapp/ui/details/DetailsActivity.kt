package com.imdb.movieapp.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.imdb.movieapp.R
import com.imdb.movieapp.data.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private var movie: Movie? = null
    private lateinit var binding: com.imdb.movieapp.databinding.ActivityDetailsBinding
    private val viewMovie: DetailsViewModel by viewModels()

    companion object {
        @JvmStatic
        fun start(context: Context, movie: Movie) {
            val starter = Intent(context, DetailsActivity::class.java)
                .putExtra("data", movie)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Movie Details"

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        if (intent.hasExtra("data"))
            movie = intent.getSerializableExtra("data") as Movie?
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        movie?.let {
            viewMovie.getMovieDetails(it.id)
            binding.item = it
            binding.lifecycleOwner = this
            binding.executePendingBindings()
        }

        viewMovie.details.observe(this) {
            if (it != null) {
                binding.details = it
                binding.executePendingBindings()

                it.videos?.let {
                    binding.rvTrailer.adapter = it.trailers?.let { it1 -> TrailerAdapter(it1) }
                }

            }
        }
    }
}