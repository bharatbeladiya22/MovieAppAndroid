package com.imdb.movieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.imdb.movieapp.data.Movie
import com.imdb.movieapp.databinding.ItemMovieBinding
import com.imdb.movieapp.ui.details.DetailsActivity

class MovieAdapter : PagingDataAdapter<Movie, MovieAdapter.ItemVH>(MovieDiffUtils) {

    inner class ItemVH(private val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Movie?) {
            if (binding is ItemMovieBinding) {
                binding.item = item
                binding.executePendingBindings()
            }
        }
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.bindData(getItem(position))
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val itemVH = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val vh = ItemVH(itemVH)
        itemVH.root.setOnClickListener {
            getItem(vh.absoluteAdapterPosition)?.let { it1 ->
                DetailsActivity.start(
                    parent.context,
                    it1
                )
            }
        }
        return vh
    }

    object MovieDiffUtils : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}