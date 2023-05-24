package com.ahmrh.pierresproduct

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.pierresproduct.ui.navigation.NavigationItem
import com.ahmrh.pierresproduct.ui.navigation.Screen
import com.ahmrh.pierresproduct.ui.theme.PierresProductTheme

@ExperimentalMaterial3Api
@Composable
fun PierresProductApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = modifier.padding(innerPadding)
        )
    }
}

@ExperimentalMaterial3Api
@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(id = R.string.menu_store),
            icon = ImageVector.vectorResource(id = R.drawable.baseline_store_24),
            screen = Screen.Store
        ),
        NavigationItem(
            title = stringResource(R.string.menu_cart),
            icon = Icons.Default.ShoppingCart,
            screen = Screen.Cart
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
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.title) },
                selected = true,
                onClick = {}
            )

        }
    }

}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PierresProductAppPreview(){
    PierresProductTheme {
        PierresProductApp()
    }
}