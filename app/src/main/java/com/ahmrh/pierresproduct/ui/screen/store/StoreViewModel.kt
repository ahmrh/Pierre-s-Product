package com.ahmrh.pierresproduct.ui.screen.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.pierresproduct.data.ProductRepository
import com.ahmrh.pierresproduct.model.Product
import com.ahmrh.pierresproduct.ui.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class StoreViewModel(
    private val repository: ProductRepository
): ViewModel(){
    private val _uiState: MutableStateFlow<UiState<List<Product>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Product>>>
        get() = _uiState

    fun getAllProducts(){
        viewModelScope.launch {
            repository.getAllProducts()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { product ->
                    _uiState.value = UiState.Success(product)
                }
        }
    }

}