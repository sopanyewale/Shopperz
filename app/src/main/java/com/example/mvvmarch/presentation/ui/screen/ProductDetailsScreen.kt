package com.example.mvvmarch.presentation.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvvmarch.presentation.viewmodel.ProductsViewModel

@Composable
fun ProductDetailsScreen(navController: NavController,
                         modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<ProductsViewModel>()

    val product = viewModel.selectedProduct.collectAsState().value

    Text(text = product.title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxSize(1f)
            .padding(16.dp))

}

@Preview(showSystemUi = true, )
@Composable
fun Preview(){
    Text(text = "Title",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(16.dp))
}