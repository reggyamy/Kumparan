package com.reggya.kumparantechnicaltest.core.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reggya.kumparantechnicaltest.detail.DetailActivity
import com.reggya.kumparantechnicaltest.core.data.PostResponseItem
import com.reggya.kumparantechnicaltest.databinding.ItemPostBinding

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var posts = ArrayList<PostResponseItem?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<PostResponseItem?>?){
        if (newData == null) return
            posts.clear()
            posts.addAll(newData)
            notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        return ViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        val post = posts[position]
        if (post != null) {
            holder.bind(post)
        }
    }

    override fun getItemCount(): Int = posts.size

    inner class ViewHolder(private val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostResponseItem) {
            binding.apply {
                binding.title.text = post.title
                binding.body.text = post.body
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DATA, post)
                itemView.context.startActivity(intent)
            }
        }
    }
}