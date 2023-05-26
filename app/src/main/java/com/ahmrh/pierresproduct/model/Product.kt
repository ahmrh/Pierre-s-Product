package com.ahmrh.pierresproduct.model

import androidx.room.Entity

data class Product(
    val id: Int,
    val name: String,
    val imgUrl: String,
    val price: Int,
    val desc: String,
    val crop: Crop,
)

