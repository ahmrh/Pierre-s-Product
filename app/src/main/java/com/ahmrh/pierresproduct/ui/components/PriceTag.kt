package com.ahmrh.pierresproduct.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmrh.pierresproduct.R



@Composable
fun PriceTag(
    price: Int,
    fontSize: TextUnit = 16.sp,
    iconSize: Dp = 18.dp,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "$price",
            fontSize = fontSize
        )
        Image(
            painter = painterResource(id = R.drawable.gold),
            contentDescription = "gold",
            Modifier
                .size(iconSize)
        )
    }
}

@Preview(showBackground = true )
@Composable
fun Preview(){
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        PriceTag(price = 100)
    }

}