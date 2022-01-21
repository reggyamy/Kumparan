package com.reggya.kumparantechnicaltest.core.domain

import com.reggya.kumparantechnicaltest.core.data.CommentsResponseItem
import com.reggya.kumparantechnicaltest.core.data.PostResponseItem
import com.reggya.kumparantechnicaltest.core.data.UserResponse
import com.reggya.kumparantechnicaltest.core.data.model.AlbulsResponseItem
import com.reggya.kumparantechnicaltest.core.data.service.ApiResponse
import io.reactivex.rxjava3.core.Flowable

interface UseCase {

    fun getPosts(): Flowable<ApiResponse<List<PostResponseItem?>?>>

    fun getUser(userId: Long): Flowable<ApiResponse<UserResponse>>

    fun getComments(postId: Long): Flowable<ApiResponse<List<CommentsResponseItem>>>

    fun getAlbums(userId: Long): Flowable<ApiResponse<List<AlbulsResponseItem?>?>>
}