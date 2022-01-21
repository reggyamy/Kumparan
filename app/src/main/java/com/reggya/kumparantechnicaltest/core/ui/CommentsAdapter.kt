package com.reggya.kumparantechnicaltest.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reggya.kumparantechnicaltest.core.data.CommentsResponseItem
import com.reggya.kumparantechnicaltest.databinding.ItemCommentBinding

class CommentsAdapter: RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    val comments = ArrayList<CommentsResponseItem?>()

    fun setData(newData: List<CommentsResponseItem?>?){
        if (newData == null) return
        comments.clear()
        comments.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = comments[position]
        if (data != null)
            holder.bind(data)
    }

    override fun getItemCount(): Int = comments.size

    inner class ViewHolder(private val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CommentsResponseItem) {
            binding.title.text = data.name
            binding.body.text = data.body
        }
    }

}
