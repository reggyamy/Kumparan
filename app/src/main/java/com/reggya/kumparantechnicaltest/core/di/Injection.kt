package com.reggya.kumparantechnicaltest.core.di

import com.reggya.kumparantechnicaltest.core.data.remote.RemoteDataSource
import com.reggya.kumparantechnicaltest.core.data.remote.Repository
import com.reggya.kumparantechnicaltest.core.data.service.ApiConfig
import com.reggya.kumparantechnicaltest.core.domain.IRepository
import com.reggya.kumparantechnicaltest.core.domain.Interactor
import com.reggya.kumparantechnicaltest.core.domain.UseCase

object Injection {

    private fun provideRepository(): IRepository{
        val remoteData = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        return Repository.getInstance(remoteData)
    }

    fun provideUseCase(): UseCase{
        val repository = provideRepository()
        return Interactor(repository)
    }
}