package com.ismail.core.domain.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.recyclerview.widget.RecyclerView
import com.ismail.core.databinding.ItemTrailerMovieBinding
import com.ismail.core.domain.model.MovieVideo
import com.ismail.core.domain.utils.commonYoutubeUrl

class MovieTrailerAdapter(private val itemClick: (MovieVideo) -> Unit) :
    RecyclerView.Adapter<MovieTrailerAdapter.MovieTrailerViewHolder>() {


    private var items: MutableList<MovieVideo> = mutableListOf()

    fun setItems(items: List<MovieVideo>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTrailerViewHolder {
        val binding = ItemTrailerMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieTrailerViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: MovieTrailerViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size


    class MovieTrailerViewHolder(
        private val binding: ItemTrailerMovieBinding,
        val itemClick: (MovieVideo) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: MovieVideo) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                binding.wvTrailer.apply {
                    settings.javaScriptEnabled = true
                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                    webChromeClient = WebChromeClient()
                    loadUrl(key.commonYoutubeUrl())
                }
            }
        }
    }

}