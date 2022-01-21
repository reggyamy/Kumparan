package com.reggya.kumparantechnicaltest.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reggya.kumparantechnicaltest.core.data.UserResponse
import com.reggya.kumparantechnicaltest.core.ui.AlbumsAdapter
import com.reggya.kumparantechnicaltest.core.ui.ViewModelFactory
import com.reggya.kumparantechnicaltest.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    companion object{
        const val USER = "user"
    }

    private lateinit var binding: ActivityUserBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<UserResponse>(USER)

        binding.apply {
            name.text = data?.name
            email.text = data?.email
            company.text = data?.company?.name
            address.text = ("${data?.address?.street}, ${data?.address?.suite}, " +
                    "${data?.address?.city}, ${data?.address?.zipcode}")

        }

        viewModel = ViewModelProvider(this, ViewModelFactory
            .getInstance(this))[DetailViewModel::class.java]
        data?.id?.let {
            viewModel.getAlbums(it.toLong()).observe(this, { albumsResponse ->
                val albumsAdapter = AlbumsAdapter()
                albumsAdapter.setData(albumsResponse.data)
                binding.rvAlbums.apply {
                    layoutManager = LinearLayoutManager(this@UserActivity)
                    setHasFixedSize(true)
                    adapter = albumsAdapter
                }
            })
        }
    }
}