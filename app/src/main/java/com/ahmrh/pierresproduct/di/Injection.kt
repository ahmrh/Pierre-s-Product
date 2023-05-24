package com.ahmrh.pierresproduct.di

import com.ahmrh.pierresproduct.data.ProductRepository

object Injection {
    fun provideRepository(): ProductRepository{
        return ProductRepository.getInstance()
    }
}