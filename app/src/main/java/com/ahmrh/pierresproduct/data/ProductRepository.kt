package com.ahmrh.pierresproduct.data

import com.ahmrh.pierresproduct.model.FakeProductDataSource
import com.ahmrh.pierresproduct.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products = mutableListOf<Product>()

    init{
        if(products.isEmpty())
            FakeProductDataSource.dummyProducts.mapTo(products){ it }
    }

    fun getAllRewards(): Flow<List<Product>> {
        return flowOf(products)
    }

    fun getRewardById(productId: Int): Product {
        return products.first {
            it.id == productId
        }
    }

    companion object {
        @Volatile
        private var instance: ProductRepository? = null

        fun getInstance(): ProductRepository =
            instance ?: synchronized(this) {
                ProductRepository().apply {
                    instance = this
                }
            }
    }
}