package com.ismail.core.domain.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ismail.core.databinding.ItemCastBinding
import com.ismail.core.domain.model.Caster

class CasterAdapter(private val itemClick: (Caster) -> Unit) :
    RecyclerView.Adapter<CasterAdapter.CasterViewHolder>() {


    private var items: MutableList<Caster> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Caster>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasterViewHolder {
        val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CasterViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: CasterViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size


    class CasterViewHolder(
        private val binding: ItemCastBinding,
        val itemClick: (Caster) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Caster) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                binding.run {
                    tvCast.text = name
                    Glide.with(itemView).load(item.img).into(ivCast)
                }
            }

        }
    }

}