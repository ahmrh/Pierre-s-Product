package com.ahmrh.pierresproduct.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ahmrh.pierresproduct.R
import com.ahmrh.pierresproduct.model.FakeProductDataSource

@ExperimentalMaterial3Api
@Composable
fun ProductItem(
    imgUrl: String,
    title: String,
    price: Int,
    modifier: Modifier = Modifier,
) {
    Column {
        ListItem(
            headlineContent = { Text(title) },
            trailingContent = { PriceTag(price) },
            leadingContent = {

                AsyncImage(
                    model = imgUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(60.dp)
                )
            }
        )
        Divider()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    val item = FakeProductDataSource.dummyProducts[0]
    ProductItem(
        item.imgUrl,
        item.name,
        item.price
    )
}

@Composable
fun PriceTag(
    price: Int
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "$price",
            fontSize = 16.sp
        )
        Image(
            painter = painterResource(id = R.drawable.gold),
            contentDescription = "gold"
        )
    }
}