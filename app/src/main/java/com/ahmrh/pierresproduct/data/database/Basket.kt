package com.ahmrh.pierresproduct.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket")
data class Basket(
    @PrimaryKey(autoGenerate = true)
    val basketId: Int,

    val productId: Int,

)
