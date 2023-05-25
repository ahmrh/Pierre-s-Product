package com.ahmrh.pierresproduct.ui.screen.store

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _uiState.value = UiState.Loading

        _query.value = newQuery
        viewModelScope.launch {
            repository.searchProduct(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { product ->
                    _uiState.value = UiState.Success(product)
                }
        }
    }


    fun getAllProducts(){
        _uiState.value = UiState.Loading

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

    fun getProducts(){

    }

}