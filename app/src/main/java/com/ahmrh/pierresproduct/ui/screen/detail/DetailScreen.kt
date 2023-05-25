package com.ahmrh.pierresproduct.ui.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahmrh.pierresproduct.di.Injection
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

@Composable
fun DetailContent(

)
{

}