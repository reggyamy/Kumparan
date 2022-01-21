package com.reggya.kumparantechnicaltest.core.data.service

import com.reggya.kumparantechnicaltest.core.data.CommentsResponse
import com.reggya.kumparantechnicaltest.core.data.CommentsResponseItem
import com.reggya.kumparantechnicaltest.core.data.PostResponseItem
import com.reggya.kumparantechnicaltest.core.data.UserResponse
import com.reggya.kumparantechnicaltest.core.data.model.AlbulsResponseItem
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    fun getPosts(): Flowable<List<PostResponseItem>>

    @GET("comments")
    fun getCommentsById(
        @Query("postId") postId: Long
    ): Flowable<List<CommentsResponseItem>>

    @GET("users/{userId}")
    fun getUsersById(
        @Path("userId") UserId: Long
    ): Flowable<UserResponse>

    @GET("users/{userId}/albums")
    fun getAlbums(
        @Path("userId") UserId: Long
    ): Flowable<List<AlbulsResponseItem>>
}