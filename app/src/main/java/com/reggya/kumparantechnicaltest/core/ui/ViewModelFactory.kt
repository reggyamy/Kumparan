package com.reggya.kumparantechnicaltest.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reggya.kumparantechnicaltest.core.di.Injection
import com.reggya.kumparantechnicaltest.core.domain.UseCase
import com.reggya.kumparantechnicaltest.detail.DetailViewModel
import com.reggya.kumparantechnicaltest.home.HomeViewModel

class ViewModelFactory private constructor(
    private val useCase: UseCase
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when{
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->{
                HomeViewModel(useCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(useCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideUseCase())
            }
    }

}