package com.ahmrh.pierresproduct.ui.screen.store

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahmrh.pierresproduct.di.Injection
import com.ahmrh.pierresproduct.model.Product
import com.ahmrh.pierresproduct.ui.components.ProductItem
import com.ahmrh.pierresproduct.ui.util.UiState
import com.ahmrh.pierresproduct.ui.util.ViewModelFactory

@Composable
fun StoreScreen(
    modifier: Modifier = Modifier,
    viewModel: StoreViewModel = viewModel(factory = ViewModelFactory(
        Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllProducts()
            }

            is UiState.Success -> {
                StoreContent(
                    modifier = modifier,
                    listProduct = uiState.data,
                    navigateToDetail = navigateToDetail,
                )
            }

            is UiState.Error -> {}
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreContent(
    modifier: Modifier = Modifier,
    listProduct: List<Product>,
    navigateToDetail: (Int) -> Unit
) {
    LazyColumn {
        items(listProduct) { product ->
            ProductItem(
                imgUrl = product.imgUrl,
                title = product.name,
                desc = product.desc,
                price = product.price
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoreScreenPreview() {
    StoreScreen(navigateToDetail = {})
}

