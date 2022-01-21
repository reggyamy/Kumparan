package com.reggya.kumparantechnicaltest.core.data.remote

import com.reggya.kumparantechnicaltest.core.data.CommentsResponseItem
import com.reggya.kumparantechnicaltest.core.data.PostResponseItem
import com.reggya.kumparantechnicaltest.core.data.UserResponse
import com.reggya.kumparantechnicaltest.core.data.model.AlbulsResponseItem
import com.reggya.kumparantechnicaltest.core.data.service.ApiResponse
import com.reggya.kumparantechnicaltest.core.domain.IRepository
import io.reactivex.rxjava3.core.Flowable

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
): IRepository{

    override fun getPost(): Flowable<ApiResponse<List<PostResponseItem?>?>> {
        return remoteDataSource.getPost()
    }

    override fun getUser(userId: Long): Flowable<ApiResponse<UserResponse>> {
        return remoteDataSource.getUserById(userId)
    }

    override fun getComments(postId: Long): Flowable<ApiResponse<List<CommentsResponseItem>>> {
        return remoteDataSource.getCommentsrById(postId)
    }

    override fun getAlbums(userId: Long): Flowable<ApiResponse<List<AlbulsResponseItem?>?>> {
        return remoteDataSource.getAlbums(userId)
    }


    companion object{
        @Volatile
        private var instance : Repository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): Repository =
            instance ?: synchronized(this){
                instance ?: Repository(remoteDataSource)
            }
    }
}