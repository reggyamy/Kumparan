package com.reggya.kumparantechnicaltest.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reggya.kumparantechnicaltest.core.data.service.ApiResponseType
import com.reggya.kumparantechnicaltest.core.ui.HomeAdapter
import com.reggya.kumparantechnicaltest.core.ui.ViewModelFactory
import com.reggya.kumparantechnicaltest.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postsAdapter = HomeAdapter()

        viewModel = ViewModelProvider(this, ViewModelFactory
            .getInstance(this))[HomeViewModel::class.java]
        viewModel.getPosts().observe( this, { response ->
            if(response != null){
                if (response.type == ApiResponseType.SUCCESS){
                    setUpLoader(ApiResponseType.SUCCESS)
                    postsAdapter.setData(response.data)
                }
                else if (response.type == ApiResponseType.LOADING)
                    setUpLoader(ApiResponseType.LOADING)
                else setUpLoader(ApiResponseType.ERROR)
            }
        })

        binding.rvPosts.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = postsAdapter
        }

    }

    private fun setUpLoader(responseType: ApiResponseType) {
        if (responseType == ApiResponseType.LOADING){
            binding.progressBar.visibility = View.VISIBLE
            binding.rvPosts.visibility = View.INVISIBLE
            binding.viewError.visibility = View.INVISIBLE
        }
        else if (responseType == ApiResponseType.SUCCESS){
            binding.progressBar.visibility = View.GONE
            binding.rvPosts.visibility = View.VISIBLE
            binding.viewError.visibility = View.INVISIBLE
        }
        else{
            binding.progressBar.visibility = View.GONE
            binding.rvPosts.visibility = View.INVISIBLE
            binding.viewError.visibility = View.VISIBLE
        }
    }

}