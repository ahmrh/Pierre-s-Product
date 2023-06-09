package com.ahmrh.pierresproduct

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.navArgument
import com.ahmrh.pierresproduct.ui.navigation.NavigationItem
import com.ahmrh.pierresproduct.ui.navigation.Screen
import com.ahmrh.pierresproduct.ui.screen.basket.BasketScreen
import com.ahmrh.pierresproduct.ui.screen.detail.DetailScreen
import com.ahmrh.pierresproduct.ui.screen.profile.ProfileScreen
import com.ahmrh.pierresproduct.ui.screen.store.StoreScreen
import com.ahmrh.pierresproduct.ui.theme.PierresProductTheme
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun PierresProductApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier,
        topBar = {
            if (currentRoute != Screen.Store.route) {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back Arrow"
                            )
                        }
                    },
                    actions = {}
                )
            }
        }
) {
    innerPadding ->
    NavHost(
        navController = navController,
        startDestination = Screen.Store.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Screen.Store.route) {
            StoreScreen(
                navigateToDetail = { productId ->
                    navController.navigate(
                        Screen.Detail.createRoute(
                            productId
                        )
                    )
                },
                addingToBasket = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Basket not implemented yet")
                    }
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
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("productId") {
                nullable = true
            })
        ) {
            val arguments = it.arguments
            Log.d(
                "Pierre's App",
                " ${arguments.toString()}"
            )

            val id =
                arguments?.getString("productId")?.toInt()
                    ?: -1
            Log.d(
                "Pierre's App",
                " ${id}"
            )
            DetailScreen(
                productId = id,
                addingToBasket = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Basket not implemented yet")
                    }
                }
            )
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
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.title
                    )
                },
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

@Composable
private fun TopBar(
    navController: NavHostController,
    modifier: Modifier
) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PierresProductAppPreview() {
    PierresProductTheme {
        PierresProductApp()
    }
}
