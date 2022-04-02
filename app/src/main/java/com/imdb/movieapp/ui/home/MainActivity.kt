package com.imdb.movieapp.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.imdb.movieapp.R
import com.imdb.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        setupView()
    }

    private fun setupView() {
        val movieAdapter = MovieAdapter()
        binding.rvMovies.adapter = movieAdapter.withLoadStateHeaderAndFooter(
            footer = MovieLoadingStateAdapter(movieAdapter),
            header = MovieLoadingStateAdapter(movieAdapter)
        )
        lifecycleScope.launch {
            viewModel.movieData.collect {
                movieAdapter.submitData(it)
            }
        }
    }
}