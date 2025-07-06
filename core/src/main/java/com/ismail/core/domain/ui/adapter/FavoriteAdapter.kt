package com.ismail.core.domain.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ismail.core.databinding.ItemListFavoriteBinding
import com.ismail.core.domain.model.Movie

class FavoriteAdapter(private val itemClick: (Movie) -> Unit) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

        private var items: MutableList<Movie> = mutableListOf()

    fun setItems(item: List<Movie>){
        this.items.clear()
        this.items.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.FavoriteViewHolder {
        val binding = ItemListFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class FavoriteViewHolder(
        private val binding: ItemListFavoriteBinding,
        val itemClick: (Movie) -> Unit
    ): RecyclerView.ViewHolder(binding.root){
        fun bindView(item: Movie){
            with(item){
                itemView.setOnClickListener{ itemClick(this)}
                binding.run {
                    tvName.text = name
                    Glide.with(itemView).load(item.img).into(ivMovieFavorite)
                }
            }
        }
    }
}