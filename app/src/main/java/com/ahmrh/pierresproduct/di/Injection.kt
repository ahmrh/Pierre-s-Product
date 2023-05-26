package com.ahmrh.pierresproduct.di

import android.app.Application
import android.content.Context
import com.ahmrh.pierresproduct.data.ProductRepository
import com.ahmrh.pierresproduct.data.database.BasketDatabase

object Injection {
    private var application: Application? = null

    private inline val requireApplication
        get() = application ?: error("Missing: Injection Application")

    fun init(application: Application) {
        this.application = application
    }

    fun provideRepository(context: Context): ProductRepository{

        val database = BasketDatabase.getDatabase(context)
        return ProductRepository.getInstance(database)
    }
}