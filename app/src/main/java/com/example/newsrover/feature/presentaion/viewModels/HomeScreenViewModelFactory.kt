package com.example.newsrover.feature.presentaion.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsrover.feature.domain.usecases.GetNewsUseCase

class HomeScreenViewModelFactory(private val getNewsUseCase: GetNewsUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        HomeScreenViewModel(getNewsUseCase) as T
}