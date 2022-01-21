package com.reggya.kumparantechnicaltest.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reggya.kumparantechnicaltest.core.data.model.AlbulsResponseItem
import com.reggya.kumparantechnicaltest.databinding.ItemAlbumsBinding

class AlbumsAdapter: RecyclerView.Adapter<AlbumsAdapter.ViewHolder>(){

    val albums = ArrayList<AlbulsResponseItem?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData : List<AlbulsResponseItem?>?){
        if (newData == null) return
        albums.clear()
        albums.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAlbumsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = albums[position]
        if (data != null) holder.bind(data)
    }

    override fun getItemCount(): Int = albums.size

    class ViewHolder(private val binding: ItemAlbumsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AlbulsResponseItem) {
            binding.title.text = data.title
        }
    }
}
