package com.imdb.movieapp.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.imdb.movieapp.R
import com.imdb.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.text.append

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

        lifecycleScope.launch {
            movieAdapter.loadStateFlow.collectLatest { loadStates ->
                // Show progress bar for initial load or refresh
                binding.layoutState.progressBar.isVisible = loadStates.refresh is LoadState.Loading

                // Show error message and retry button for initial load or refresh error
                val refreshError = loadStates.refresh as? LoadState.Error
                binding.layoutState.errorMsg.isVisible = refreshError != null
                binding.layoutState.retryButton.isVisible = refreshError != null
                binding.layoutState.errorMsg.text = refreshError?.error?.localizedMessage

                // Hide RecyclerView if loading or error during refresh
                binding.rvMovies.isVisible = loadStates.source.refresh is LoadState.NotLoading

                // Optional: Handle empty state after initial load
                if (loadStates.source.refresh is LoadState.NotLoading &&
                    loadStates.append.endOfPaginationReached &&
                    movieAdapter.itemCount == 0) {
                    // binding.emptyView.isVisible = true // Show your empty state view
                    // binding.rvMovies.isVisible = false
                } else {
                    // binding.emptyView.isVisible = false
                }
            }
        }

        binding.layoutState.retryButton.setOnClickListener {
            movieAdapter.retry()
        }

    }
}