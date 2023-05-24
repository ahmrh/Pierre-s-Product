package com.ahmrh.pierresproduct.ui.navigation

sealed class Screen(val route: String) {
    object Store: Screen("store")
    object Cart: Screen("cart")
    object Profile: Screen("profile")
    object Detail: Screen("store/{productId}"){
        fun createRoute(productId: Int) = "store/$productId"
    }
}
