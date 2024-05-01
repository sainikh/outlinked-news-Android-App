package com.example.newsrover.feature.presentaion.viewModels
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsrover.feature.data.sources.models.NewsArticle
import com.example.newsrover.feature.domain.usecases.GetNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {
    var news = mutableStateListOf<NewsArticle>()
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading

    fun fetchNews(){
            viewModelScope.launch {
                try {
                    val list = getNewsUseCase.execute().toMutableStateList()
                    news.addAll(list)
                    _isLoading.value = false
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }
}