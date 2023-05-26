package com.ahmrh.pierresproduct.ui.screen.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahmrh.pierresproduct.R
import com.ahmrh.pierresproduct.di.Injection
import com.ahmrh.pierresproduct.model.Product
import com.ahmrh.pierresproduct.ui.components.ProductItem
import com.ahmrh.pierresproduct.ui.util.UiState
import com.ahmrh.pierresproduct.ui.util.ViewModelFactory

@Composable
fun StoreScreen(
    modifier: Modifier = Modifier,
    viewModel: StoreViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateToDetail: (Int) -> Unit,
    addingToBasket: () -> Unit,
    navigateToProfile: () -> Unit,
) {
    val query by viewModel.query

    Box{
        Column(
            modifier = Modifier.
            padding(16.dp)
        ){
            TopBar(
                query = query,
                onQueryChange = viewModel::search,
                navigateToProfile = navigateToProfile
            )
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


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreContent(
    modifier: Modifier = Modifier,
    listProduct: List<Product>,
    navigateToDetail: (Int) -> Unit,
) {
    LazyColumn(
        modifier.testTag("ProductList")
    ) {
        items(listProduct) { product ->
            ProductItem(
                imgUrl = product.imgUrl,
                title = product.name,
                price = product.price,
                modifier = Modifier.clickable {
                    navigateToDetail(product.id)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit,
    query: String,
    navigateToProfile: () -> Unit
) {
    var active by rememberSaveable { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,

    ) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = { active = false },
            active = active,
            onActiveChange = { active = false },
            placeholder = { Text("Search product") },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {},
            modifier = Modifier
                .weight(1f)
        ) {}

        IconButton(
            onClick = {
                navigateToProfile()
            },
            modifier = Modifier
                .padding(start = 16.dp)
                .size(56.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.me),
                contentDescription = "Profile",
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoreScreenPreview() {
    StoreScreen(
        navigateToDetail = {},
        addingToBasket = {},
        navigateToProfile = {}
    )
}

