package com.ahmrh.pierresproduct.ui.navigation

sealed class Screen(val route: String) {
    object Store: Screen("store")
    object Basket: Screen("basket")
    object Profile: Screen("profile")
    object Detail: Screen("store/{productId}"){
        fun createRoute(productId: Int) = "store/$productId"
    }
}
