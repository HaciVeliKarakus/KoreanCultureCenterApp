package io.hvk.koreanculturecenterapp.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.hvk.koreanculturecenterapp.data.Detail
import io.hvk.koreanculturecenterapp.repository.DetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val repository = DetailsRepository()

    private val _newsItems = MutableStateFlow(Detail())
    val newsItems: StateFlow<Detail> = _newsItems.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()


    fun loadNews(link: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _newsItems.value = repository.getData(link)
            _isLoading.value = false
        }
    }
} 