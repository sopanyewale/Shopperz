package com.example.mvvmarch.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
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
import com.example.mvvmarch.domain.capitalizeWords
import com.example.mvvmarch.presentation.viewmodel.ProductsViewModel

@Composable
fun ProductDetailsScreen(
    navController: NavController,
    viewModel: ProductsViewModel,
    modifier: Modifier = Modifier
) {

    val product = viewModel.selectedProduct.collectAsState().value
    val quantity = remember { mutableIntStateOf(1) }

    LaunchedEffect(Unit){
        viewModel.toastEvent.collect { message ->
            Toast.makeText(navController.context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = product.image,
            contentDescription = product.title,
            contentScale = ContentScale.Fit,
            placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
            modifier = Modifier
                .fillMaxWidth(1f)
                .aspectRatio(16f / 9f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = product.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(1f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = product.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Category: ${product.category.capitalizeWords()}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Price: \u20B9 ${product.price}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Rating: ${product.rating.rate} | (${product.rating.count})",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        QuantitySelector(
            quantity = quantity.intValue,
            onIncrease = {
                quantity.intValue++
            },
            onDecrease = {
                quantity.intValue--
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black,
            disabledContentColor = Color.Gray,
            disabledContainerColor = Color.Transparent
        ),
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(16.dp),
            onClick = {
                viewModel.addProductToCart(product.copy(orderQuantity = quantity.intValue))
            }) {
            Text(
                text = "Add to Cart",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(16.dp)
            )
        }

    }

}

@Composable
fun QuantitySelector(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = "Quantity: ")

        if (quantity > 1) {
            OutlinedButton(
                onClick = onDecrease,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(text = "-")
            }
        } else {
            OutlinedButton(
                onClick = onDecrease,
                enabled = false,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(text = "-")
            }
        }

        Text(
            text = "$quantity",
            modifier = Modifier
                .padding(end = 8.dp)
                .padding(start = 8.dp, end = 8.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
        OutlinedButton(
            onClick = onIncrease,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text(text = "+")
        }
    }
}