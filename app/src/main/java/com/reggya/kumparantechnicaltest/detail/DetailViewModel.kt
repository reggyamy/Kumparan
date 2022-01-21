package com.reggya.kumparantechnicaltest.detail

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.reggya.kumparantechnicaltest.core.domain.UseCase

class DetailViewModel(
    private val useCase: UseCase
):ViewModel() {

    fun getUser(userId: Long) = LiveDataReactiveStreams.fromPublisher(useCase.getUser(userId))
    fun getComments(postId: Long) = LiveDataReactiveStreams.fromPublisher(useCase.getComments(postId))
    fun getAlbums(userId: Long) = LiveDataReactiveStreams.fromPublisher(useCase.getAlbums(userId))
}