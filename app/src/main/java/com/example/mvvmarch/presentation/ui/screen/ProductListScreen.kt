package com.example.mvvmarch.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvvmarch.domain.capitalizeWords
import com.example.mvvmarch.presentation.ui.util.ProductItem
import com.example.mvvmarch.presentation.viewmodel.ProductsViewModel

@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductsViewModel,
    modifier: Modifier = Modifier,
    category: String
) {

    val products = viewModel.productsList.collectAsState().value

    LaunchedEffect(key1 = true) {
        viewModel.getProductsByCategory(category)
    }
    if (products.isEmpty()) {
        Text(
            text = "No products found",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(1f),
            textAlign = TextAlign.Center
        )
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = category.capitalizeWords(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(1f),
            textAlign = TextAlign.Center

        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            content = {
                items(products.size) {
                    ProductItem(item = products[it]) {
                        viewModel.onProductSelected(products[it])
                        navController.navigate("productDetails")
                    }
                }
            }
        )
    }
}