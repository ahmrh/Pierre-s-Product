package com.ahmrh.pierresproduct.ui.screen.basket

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.pierresproduct.data.ProductRepository
import com.ahmrh.pierresproduct.model.Product
import com.ahmrh.pierresproduct.ui.util.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BasketViewModel(var repository: ProductRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Pair<Product, Int>>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Pair<Product, Int >>>>
        get() = _uiState


    private val _isBasketEmpty = MutableStateFlow(true)
    val isBasketEmpty: StateFlow<Boolean>
        get() = _isBasketEmpty

    fun checkBasketEmpty() {
        viewModelScope.launch {
            repository.basketIsEmpty()
                .collect {
                    _isBasketEmpty.value = it
                }
        }
    }

    fun getCount(id: Int): Flow<Int> {
        return repository.getProductCount(id)
    }

    fun addProduct(id: Int){
        viewModelScope.launch {
            Log.d("BasketViewModel", "adding data with $id")
            repository.insertToBasket(id)
        }
    }

    fun removeProduct(id: Int){
        viewModelScope.launch{
            repository.removeLastInsertedProduct(id)
        }
    }

    fun getBasketProducts(){
        viewModelScope.launch{
            repository.getAllProductsInBasket()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { basketProducts ->
                    _uiState.value = UiState.Success(basketProducts)
                }
        }
    }

}