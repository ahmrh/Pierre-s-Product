package com.ahmrh.pierresproduct.ui.screen.detail

import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahmrh.pierresproduct.R
import com.ahmrh.pierresproduct.di.Injection
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
    navigateBack: () -> Unit,
    navigateToBasket: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getProductById(productId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContent(
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column {
            Top()
            Center()
            
        }


    }
}


@Composable
fun Top() {
    Row(
        modifier = Modifier
            .padding(bottom = 16.dp)

    ) {
        Image(
            painterResource(id = R.drawable.omori),
            contentDescription = "",
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
                text = "Pumpkin Seeds",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
            PriceTag(
                price = 100,
                fontSize = 24.sp,
                iconSize = 24.dp,

            )

        }
    }
}

@Composable
fun Center(){
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ){
        Text("Plant these in the fall. Takes 13 days to mature.",
             style = MaterialTheme.typography.bodyLarge)

        CropInformation()

    }
}

@Composable
fun CropInformation(){

    Column(
    ){

        Text(
            text = "Crop Information",
            style = MaterialTheme.typography.titleLarge
        )

        Column(
            Modifier.background(MaterialTheme.colorScheme.surfaceVariant)
        ){
            Row(){
                Text("Name")
                Text("babi")
            }

        }
    }
}