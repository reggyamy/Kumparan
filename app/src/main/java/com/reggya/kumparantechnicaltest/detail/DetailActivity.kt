package com.reggya.kumparantechnicaltest.detail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reggya.kumparantechnicaltest.core.data.PostResponseItem
import com.reggya.kumparantechnicaltest.core.ui.CommentsAdapter
import com.reggya.kumparantechnicaltest.core.ui.ViewModelFactory
import com.reggya.kumparantechnicaltest.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object{
        const val DATA = "data"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var data : PostResponseItem? = null
    var sizeComments : Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = intent.getParcelableExtra(DATA)

        setUpViewModel()
        setUpUi()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory
            .getInstance(this))[DetailViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun setUpUi() {
        //User Info
        data?.userId?.let { userId ->
            viewModel.getUser(userId.toLong()).observe(this, { userResponse ->
                binding.name.text = userResponse.data?.username
                binding.email.text = userResponse.data?.email

                binding.name.setOnClickListener{
                    val intent = Intent(this, UserActivity::class.java)
                    intent.putExtra(UserActivity.USER, userResponse.data)
                    this.startActivity(intent)
                }
            })
        }

        //Comments Info
        data?.id?.let {  postId ->
            viewModel.getComments(postId.toLong()).observe(this,{ commentsResponse ->
                val commentsAdapter = CommentsAdapter()
                binding.sizeComments.text = ("${commentsResponse.data?.size} comments")
                commentsAdapter.setData(commentsResponse.data)
                commentsAdapter.notifyDataSetChanged()
                binding.rvComments.apply {
                    layoutManager = LinearLayoutManager(this@DetailActivity)
                    setHasFixedSize(true)
                    adapter = commentsAdapter
                }
            })
        }

        binding.title.text = data?.title
        binding.body.text = data?.body

    }
}