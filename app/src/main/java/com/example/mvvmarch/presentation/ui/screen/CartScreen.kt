package com.example.mvvmarch.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvvmarch.presentation.ui.util.ProductItem
import com.example.mvvmarch.presentation.viewmodel.CartViewModel

@Composable
fun CartScreen(
    modifier: Modifier,
    navController: NavController
) {
    val viewModel: CartViewModel = hiltViewModel()
    val cartItems = viewModel.cartItems.collectAsState().value

    val reloadItems = viewModel.reloadItems.collectAsState().value

    LaunchedEffect(key1 = reloadItems) {
        viewModel.getCartItems()
    }

    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize(), content = {
            items(count = cartItems.size) { index ->
                val item = cartItems[index]
                ProductItem(
                    item = item,
                    isFromCart = true,
                    onRightIconClick = {
                        // Handle right icon click, e.g., remove item from cart
                        viewModel.removeFromCart(item.id)
                    },
                    onClick = {
                        // Handle item click, e.g., navigate to product details
                        //navController.navigate("product_details/${item.id}")
                    }
                )
            }
        })
    }
}