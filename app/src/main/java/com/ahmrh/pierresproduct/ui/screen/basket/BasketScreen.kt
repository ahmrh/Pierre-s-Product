package com.ahmrh.pierresproduct.ui.screen.basket

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.ahmrh.pierresproduct.R
import com.ahmrh.pierresproduct.di.Injection
import com.ahmrh.pierresproduct.model.FakeProductDataSource
import com.ahmrh.pierresproduct.model.Product
import com.ahmrh.pierresproduct.ui.components.PriceTag
import com.ahmrh.pierresproduct.ui.util.UiState
import com.ahmrh.pierresproduct.ui.util.ViewModelFactory

@Composable
fun BasketScreen(
    modifier: Modifier = Modifier,
    viewModel: BasketViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(LocalContext.current)
        )
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getBasketProducts()
            }

            is UiState.Success -> {
                val basketProducts = uiState.data
                BasketContent(
                    basketProducts,
                    viewModel
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun BasketContent(
    basketProducts: List<Pair<Product, Int>>,
    viewModel: BasketViewModel
) {

    viewModel.checkBasketEmpty()
    val isBasketEmpty by viewModel.isBasketEmpty.collectAsState()

    if(isBasketEmpty){
        Text("Please add product first")
    } else{

        LazyColumn(
        ) {
            items(basketProducts) { product ->
                var count by remember { mutableStateOf(product.second) }
                LaunchedEffect(key1 = product.first.id) {
                    viewModel.getCount(product.first.id)
                        .collect { newCount ->
                            count = newCount
                        }
                }
                BasketItem(
                    product = product.first,
                    count = count,
                    addProduct = {
                        viewModel.addProduct(product.first.id)
                    },
                    removeProduct = {
                        viewModel.removeProduct(product.first.id)
                    },
                )
            }
        }
    }

}

@Composable
fun BasketItem(
    product: Product,
    count: Int,
    addProduct: () -> Unit,
    removeProduct: () -> Unit,
    modifier: Modifier = Modifier,
) {

    ListItem(
        headlineContent = {
            Text(
                product.name,
                style = MaterialTheme.typography.titleLarge
            )
        },
        trailingContent = {
            AddRemoveButton(
                add = {
                    addProduct()
                },
                remove = {
                    removeProduct()
                },
                count
            )

        },
        leadingContent = {

            AsyncImage(
                model = product.imgUrl,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(56.dp)
            )
        }
    )

    Divider()
}

@Composable
fun AddRemoveButton(
    add: () -> Unit,
    remove: () -> Unit,
    count: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        IconButton(
            onClick = { add() },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        Text(
            count.toString(),
            style = MaterialTheme.typography.titleLarge
        )

        IconButton(
            onClick = { remove() }, modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .size(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_remove_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun Preview() {
    BasketItem(
        FakeProductDataSource.dummyProducts[0],
        2,
        {},
        {}
    )

}