package com.reggya.kumparantechnicaltest.core.domain

import com.reggya.kumparantechnicaltest.core.data.CommentsResponseItem
import com.reggya.kumparantechnicaltest.core.data.PostResponseItem
import com.reggya.kumparantechnicaltest.core.data.UserResponse
import com.reggya.kumparantechnicaltest.core.data.model.AlbulsResponseItem
import com.reggya.kumparantechnicaltest.core.data.service.ApiResponse
import io.reactivex.rxjava3.core.Flowable

class Interactor(private val iRepository: IRepository): UseCase {
    override fun getPosts(): Flowable<ApiResponse<List<PostResponseItem?>?>> {
        return iRepository.getPost()
    }

    override fun getUser(userId: Long): Flowable<ApiResponse<UserResponse>> {
        return iRepository.getUser(userId)
    }

    override fun getComments(postId: Long): Flowable<ApiResponse<List<CommentsResponseItem>>> {
        return iRepository.getComments(postId)
    }

    override fun getAlbums(userId: Long): Flowable<ApiResponse<List<AlbulsResponseItem?>?>> {
        return iRepository.getAlbums(userId)
    }
}