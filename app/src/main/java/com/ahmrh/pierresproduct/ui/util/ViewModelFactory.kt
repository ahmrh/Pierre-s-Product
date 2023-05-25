package com.ahmrh.pierresproduct.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahmrh.pierresproduct.data.ProductRepository
import com.ahmrh.pierresproduct.ui.screen.basket.BasketViewModel
import com.ahmrh.pierresproduct.ui.screen.detail.DetailViewModel
import com.ahmrh.pierresproduct.ui.screen.store.StoreViewModel

class ViewModelFactory(private val repository: ProductRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoreViewModel::class.java)) {
            return StoreViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(BasketViewModel::class.java)) {
            return BasketViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}