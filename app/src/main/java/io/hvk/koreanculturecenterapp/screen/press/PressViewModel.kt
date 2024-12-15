package io.hvk.koreanculturecenterapp.screen.press

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.hvk.koreanculturecenterapp.data.Press
import io.hvk.koreanculturecenterapp.repository.PressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PressViewModel : ViewModel() {
    private val repository = PressRepository()

    private val _newsItems = MutableStateFlow<List<Press>>(emptyList())
    val items: StateFlow<List<Press>> = _newsItems.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            _isLoading.value = true
            _newsItems.value = repository.getData()
            _isLoading.value = false
        }
    }
} 