package com.imdb.movieapp.ui.details

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imdb.movieapp.data.Trailer
import com.imdb.movieapp.databinding.ItemTrailerBinding
import com.imdb.movieapp.utils.Constants

class TrailerAdapter(private val data: List<Trailer>) :
    RecyclerView.Adapter<TrailerAdapter.ItemVH>() {

    inner class ItemVH(private val binding: ItemTrailerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Trailer) {
            binding.item = item
            binding.executePendingBindings()

        }
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.bindData(getItem(position))
    }

    private fun getItem(position: Int): Trailer {
        return data[position]
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val itemVH = ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val vh = ItemVH(itemVH)
        itemVH.root.setOnClickListener {
            getItem(vh.absoluteAdapterPosition).let { trailer ->
                val appIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("vnd.youtube:" + trailer.key)
                )
                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(Constants.YOUTUBE_WEB_URL + trailer.key)
                )
                if (appIntent.resolveActivity(parent.context.packageManager) != null) {
                    parent.context.startActivity(appIntent)
                } else {
                    parent.context.startActivity(webIntent)
                }
            }
        }
        return vh
    }


    override fun getItemCount(): Int {
        return data.size

    }

}