package com.reggya.kumparantechnicaltest.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.reggya.kumparantechnicaltest.core.domain.UseCase

class HomeViewModel (private val useCase: UseCase): ViewModel(){

    fun getPosts() = LiveDataReactiveStreams.fromPublisher(useCase.getPosts())

}