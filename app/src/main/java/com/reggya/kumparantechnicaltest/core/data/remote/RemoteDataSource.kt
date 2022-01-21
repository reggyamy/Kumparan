package com.reggya.kumparantechnicaltest.core.data.remote

import android.annotation.SuppressLint
import android.util.Log
import com.reggya.kumparantechnicaltest.core.data.CommentsResponseItem
import com.reggya.kumparantechnicaltest.core.data.PostResponseItem
import com.reggya.kumparantechnicaltest.core.data.UserResponse
import com.reggya.kumparantechnicaltest.core.data.model.AlbulsResponseItem
import com.reggya.kumparantechnicaltest.core.data.service.ApiResponse
import com.reggya.kumparantechnicaltest.core.data.service.ApiService
import com.reggya.kumparantechnicaltest.core.utils.EspressoIdlingResource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class RemoteDataSource private constructor(private val apiService: ApiService) {

    @SuppressLint("LongLogTag")
    fun getPost(): Flowable<ApiResponse<List<PostResponseItem?>?>>{
        val resultData = PublishSubject.create<ApiResponse<List<PostResponseItem?>?>>()

        EspressoIdlingResource.increment()
        val client = apiService.getPosts()
        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                val dataArray = it
                resultData.onNext(
                    if (dataArray.isNotEmpty()) ApiResponse.success(dataArray)
                    else ApiResponse.loading()
                )
            }, { error ->
                resultData.onNext(ApiResponse.error(error.message.toString()))
                Log.e("RemoteDataSource getPosts error", error.toString())
                EspressoIdlingResource.decrement()
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("LongLogTag")
    fun getUserById(userId: Long): Flowable<ApiResponse<UserResponse>>{
        val resultData = PublishSubject.create<ApiResponse<UserResponse>>()

        EspressoIdlingResource.increment()
        val client = apiService.getUsersById(userId)
        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                resultData.onNext(
                    if (it.id != null) ApiResponse.success(it)
                    else ApiResponse.loading()
                )
            }, { error ->
                resultData.onNext(ApiResponse.error(error.message.toString()))
                Log.e("RemoteDataSource getUser error", error.toString())
                EspressoIdlingResource.decrement()
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("LongLogTag")
    fun getCommentsrById(postId: Long): Flowable<ApiResponse<List<CommentsResponseItem>>>{
        val resultData = PublishSubject.create<ApiResponse<List<CommentsResponseItem>>>()

        EspressoIdlingResource.increment()
        val client = apiService.getCommentsById(postId)
        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                val dataArray = it
                resultData.onNext(
                    (if (dataArray.isNotEmpty()) ApiResponse.success(dataArray)
                    else ApiResponse.loading()) as ApiResponse<List<CommentsResponseItem>>?
                )
            }, { error ->
                resultData.onNext(ApiResponse.error(error.message.toString()))
                Log.e("RemoteDataSource getUser error", error.toString())
                EspressoIdlingResource.decrement()
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("LongLogTag")
    fun getAlbums(userId: Long): Flowable<ApiResponse<List<AlbulsResponseItem?>?>>{
        val resultData = PublishSubject.create<ApiResponse<List<AlbulsResponseItem?>?>>()

        EspressoIdlingResource.increment()
        val client = apiService.getAlbums(userId)
        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                val dataArray = it
                resultData.onNext(
                    if (dataArray.isNotEmpty()) ApiResponse.success(dataArray)
                    else ApiResponse.loading()
                )
            }, { error ->
                resultData.onNext(ApiResponse.error(error.message.toString()))
                Log.e("RemoteDataSource getPosts error", error.toString())
                EspressoIdlingResource.decrement()
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(apiService)
            }
    }
}