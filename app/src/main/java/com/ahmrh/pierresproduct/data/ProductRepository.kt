package com.ahmrh.pierresproduct.data

import com.ahmrh.pierresproduct.data.database.BasketDatabase
import com.ahmrh.pierresproduct.data.database.ProductCount
import com.ahmrh.pierresproduct.model.FakeProductDataSource
import com.ahmrh.pierresproduct.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository(private val basketDatabase: BasketDatabase){
    private val products = mutableListOf<Product>()

    init {
        if (products.isEmpty())
            FakeProductDataSource.dummyProducts.mapTo(
                products
            ) { it }
    }

    fun getAllProducts(): Flow<List<Product>> {
        return flowOf(products)
    }

    fun getProductById(productId: Int): Product {
        return products.first {
            it.id == productId
        }
    }

    suspend fun insertToBasket(productId: Int) {
        basketDatabase.basketDao().insert(productId)
    }

    suspend fun removeLastInsertedProduct(productId: Int) {
        basketDatabase.basketDao().deleteLastInsertedRow(productId)
    }

    suspend fun basketIsEmpty(): Flow<Boolean> {
        return flowOf(basketDatabase.basketDao().getBasketProductsCount() == 0)
    }

    suspend fun getAllProductsInBasket(): Flow<List<Pair<Product, Int>>>{
        val distinctProductCounts = basketDatabase.basketDao().getDistinctProductCounts()
        val basketProducts = mutableListOf<Pair<Product, Int>>()
        for (productCount in distinctProductCounts) {
            val product = getProductById(productCount.productId)
            basketProducts.add(product to productCount.count)
        }

        return flowOf(basketProducts)
    }

    fun getProductCount(id: Int) : Flow<Int>{
        return flowOf(basketDatabase.basketDao().getCount(id))
    }

    fun searchProduct(query: String): Flow<List<Product>> {
        return flowOf(
            products.filter {
                it.name.contains(query, ignoreCase = true)
            }
        )
    }


    companion object {
        @Volatile
        private var instance: ProductRepository? = null

        fun getInstance(basketDatabase: BasketDatabase): ProductRepository =
            instance ?: synchronized(this) {
                ProductRepository(basketDatabase).apply {
                    instance = this
                }
            }
    }
}