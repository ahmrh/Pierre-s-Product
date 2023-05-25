package com.ahmrh.pierresproduct

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahmrh.pierresproduct.ui.navigation.NavigationItem
import com.ahmrh.pierresproduct.ui.navigation.Screen
import com.ahmrh.pierresproduct.ui.screen.basket.BasketScreen
import com.ahmrh.pierresproduct.ui.screen.profile.ProfileScreen
import com.ahmrh.pierresproduct.ui.screen.store.StoreScreen
import com.ahmrh.pierresproduct.ui.theme.PierresProductTheme

@ExperimentalMaterial3Api
@Composable
fun PierresProductApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Store.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Store.route) {
                StoreScreen(
                    navigateToDetail = { productId ->
                        navController.navigate(Screen.Detail.createRoute(productId))
                    },
                    navigateToBasket = {
                        navController.navigate(Screen.Basket.route)
                    },
                    navigateToProfile = {
                        navController.navigate(Screen.Profile.route)
                    }
                )
            }
            composable(Screen.Basket.route) {
                BasketScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun BottomNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(id = R.string.menu_store),
            icon = ImageVector.vectorResource(id = R.drawable.baseline_store_24),
            screen = Screen.Store
        ),
        NavigationItem(
            title = stringResource(R.string.menu_cart),
            icon = Icons.Default.ShoppingCart,
            screen = Screen.Basket
        ),
        NavigationItem(
            title = stringResource(R.string.menu_profile),
            icon = Icons.Default.AccountCircle,
            screen = Screen.Profile
        ),
    )
    NavigationBar(
        modifier = modifier
    ) {
        navigationItems.map { item ->

            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true

                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PierresProductAppPreview(){
    PierresProductTheme {
        PierresProductApp()
    }
}
