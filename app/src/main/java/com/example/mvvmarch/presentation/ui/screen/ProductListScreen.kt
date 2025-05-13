package com.example.mvvmarch.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mvvmarch.data.model.ProductsItem
import com.example.mvvmarch.domain.capitalizeWords
import com.example.mvvmarch.domain.model.Product
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


@Composable
fun ProductItem(item: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(8.dp)
                .clickable {
                    onClick()
                }

        ) {
            AsyncImage(
                model = item.image,
                contentDescription = "Product image",
                placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                modifier = Modifier
                    .width(80.dp)
                    .aspectRatio(3f / 4f),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "\u20B9 ${item.price}", style = MaterialTheme.typography.labelMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = android.R.drawable.star_on),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.1f)
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item.rating.rate.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "(${item.rating.count})",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}