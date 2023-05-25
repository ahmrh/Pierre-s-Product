package com.ahmrh.pierresproduct.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.pierresproduct.data.ProductRepository
import com.ahmrh.pierresproduct.model.Product
import com.ahmrh.pierresproduct.ui.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(var repository: ProductRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Product>> = MutableStateFlow(
        UiState.Loading)
    val uiState: StateFlow<UiState<Product>>
        get() = _uiState

    fun getProductById(productId: Int) {
        viewModelScope.launch{
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getProductById(productId))
        }
    }

}