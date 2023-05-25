package com.ahmrh.pierresproduct.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ahmrh.pierresproduct.model.FakeProductDataSource

@ExperimentalMaterial3Api
@Composable
fun ProductItem(
    imgUrl: String,
    title: String,
    desc: String,
    price: Int,
    modifier: Modifier = Modifier,
){
    Column{
        ListItem(
            headlineContent = { Text(title)},
            supportingContent = { Text(desc) },
            trailingContent = { Text("$price g") },
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
fun ProductItemPreview(){
    val item = FakeProductDataSource.dummyProducts[0]
    ProductItem(item.imgUrl, item.name, item.desc, item.price)
}