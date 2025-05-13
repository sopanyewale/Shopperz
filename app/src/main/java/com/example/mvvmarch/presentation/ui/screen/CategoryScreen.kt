package com.example.mvvmarch.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvvmarch.presentation.viewmodel.CategoryViewModel


@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    categoryViewModel: CategoryViewModel = hiltViewModel(),

) {
    val categories = categoryViewModel.productCategories.collectAsState().value

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = "Product Categories",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(16.dp),
            textAlign = TextAlign.Center
        )


       Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(2),
            content = {
                items(categories.size) {
                    GridItem(
                        onClick = {
                            navController.navigate("productList/${categories[it].name}")
                        },
                        imageRes = categories[it].imageRes,
                        title = categories[it].displayName,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            })
    }

    LaunchedEffect(key1 = true) {
        categoryViewModel.getProductCategories()
    }
}

@Composable
fun GridItem(
    onClick: () -> Unit, imageRes: Int, title: String, modifier: Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(16f / 9f)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 0f,
                            endY = 100f
                        )
                    )
                    .padding(8.dp)
            ) {
                Text(
                    text = title, style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
            }
        }
    }
}