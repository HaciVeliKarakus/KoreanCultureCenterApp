package io.hvk.koreanculturecenterapp.screen.news

import io.hvk.koreanculturecenterapp.repository.NewsRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.hvk.koreanculturecenterapp.data.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()
    
    private val _newsItems = MutableStateFlow<List<News>>(emptyList())
    val newsItems: StateFlow<List<News>> = _newsItems.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadNews()
    }
    
    private fun loadNews() {
        viewModelScope.launch {
            _isLoading.value = true
            _newsItems.value = repository.getNews()
            _isLoading.value = false
        }
    }
} 