package com.ahmrh.pierresproduct.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.ahmrh.pierresproduct.di.Injection
import com.ahmrh.pierresproduct.model.Crop
import com.ahmrh.pierresproduct.model.FakeProductDataSource
import com.ahmrh.pierresproduct.model.Product
import com.ahmrh.pierresproduct.ui.components.PriceTag
import com.ahmrh.pierresproduct.ui.util.UiState
import com.ahmrh.pierresproduct.ui.util.ViewModelFactory

@Composable
fun DetailScreen(
    productId: Int,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    addingToBasket: () -> Unit
) {
    Log.d("DetailScreen", "$productId")
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getProductById(productId)
            }

            is UiState.Success -> {
                val product = uiState.data
                DetailContent(product, addingToBasket)
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    product: Product,
    addingToBasket: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Header(
                product.name,
                product.price,
                product.imgUrl
            )
            Body(
                product
            )
        }
        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            containerColor = MaterialTheme.colorScheme.onPrimary,
            actions = {
            },
            floatingActionButton = {
                Button(
                    onClick = {
                        addingToBasket()
                    },
                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                ) {
                    Icon(
                        Icons.Filled.ShoppingCart,
                        contentDescription = "Add to Basket",
                        modifier = Modifier.size(
                            ButtonDefaults.IconSize
                        )
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Add to Basket")
                }
            }
        )
    }
}


@Composable
fun Header(
    title: String,
    price: Int,
    imgUrl: String
) {
    Row(
        modifier = Modifier
            .padding(bottom = 16.dp)

    ) {
        AsyncImage(
            model = imgUrl,
            contentDescription = "Prodcut Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(96.dp)
                .clip(RoundedCornerShape(8.dp)),
        )
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .weight(1f),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 8.dp),
            )
            PriceTag(
                price = price,
                fontSize = 24.sp,
                iconSize = 24.dp,
            )

        }
    }


}

@Composable
fun Body(
    product: Product
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            product.desc,
            style = MaterialTheme.typography.bodyLarge
        )

        CropInformation(
            product.crop
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {

                Text(
                    product.crop.desc,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            AsyncImage(
                model = product.crop.imgUrl,
                contentDescription = "Crop Image",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(96.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )


        }
    }
}

@Composable
fun CropInformation(
    crop: Crop
) {
    Column(
    ) {

        Column(

            Modifier
                .padding(vertical = 8.dp)
        ) {
            TableRow("Crop", crop.name)
            TableRow("Growth Time", crop.growthTime)
            TableRow("Season", crop.season)
            TableRowPrice("Sell Price", crop.sellPrice)
        }

    }
}

@Composable
fun TableRow(
    left: String,
    right: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(
                    0.5.dp,
                    MaterialTheme.colorScheme.onSurfaceVariant
                ),
            )


    ) {
        Text(
            text = left,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )

        )
        Text(
            text = right,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        )

    }
}

@Composable
fun TableRowPrice(
    left: String,
    price: Int
) {

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(
                    0.5.dp,
                    MaterialTheme.colorScheme.onSurfaceVariant
                ),
            )


    ) {
        Text(
            text = left,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )

        )
        PriceTag(
            price = price,
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailContent(
        product = FakeProductDataSource.dummyProducts[0],
        {}
    )
}