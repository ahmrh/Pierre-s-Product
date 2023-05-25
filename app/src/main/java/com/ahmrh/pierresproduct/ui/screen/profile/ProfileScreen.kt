package com.ahmrh.pierresproduct.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmrh.pierresproduct.R

@Composable
fun ProfileScreen(

) {
    Creator()
}

@Composable
fun Creator()
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ){
        Image(
            painterResource(id = R.drawable.me),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)

        )
        Text(
            text = "Ahmad Rolandi Hernafahreza",
            fontWeight = FontWeight.Bold,
            style= MaterialTheme.typography.titleLarge
        )
        Text(
            text = "ahmadrolandi01@gmail.com",
            style= MaterialTheme.typography.titleMedium
        )

        Text(
            text = "I write code, do interesting thing, and currently a Bangkit Academy 2023 cohort with Android Development as a Learning Path. \n\nAlso, any kind of feedback about this app is really appreciated",
            style= MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center

        )

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen()
}