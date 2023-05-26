package com.ahmrh.pierresproduct.data.database

import android.provider.ContactsContract
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmrh.pierresproduct.model.Product

@Dao
interface BasketDao {

    @Query("INSERT INTO basket (productId) VALUES (:id)")
    suspend fun insert(id: Int)

    @Query("SELECT COUNT(*) from basket WHERE productId=:id")
    fun getCount(id: Int): Int

    @Query("DELETE FROM basket WHERE productId = :id AND basketId IN (SELECT MAX(basketId) FROM basket)")
    suspend fun deleteLastInsertedRow(id: Int)

    @Query("SELECT productId, COUNT(*) as count FROM basket GROUP BY productId")
    suspend fun getDistinctProductCounts(): List<ProductCount>

    @Query("SELECT COUNT(*) FROM basket")
    suspend fun getBasketProductsCount(): Int
}