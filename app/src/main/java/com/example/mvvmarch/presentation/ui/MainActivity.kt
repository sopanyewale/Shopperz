package com.example.mvvmarch.presentation.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mvvmarch.R
import com.example.mvvmarch.presentation.ui.screen.CategoryScreen
import com.example.mvvmarch.presentation.ui.screen.ProductDetailsScreen
import com.example.mvvmarch.presentation.ui.screen.ProductListScreen
import com.example.mvvmarch.presentation.viewmodel.NotificationViewModel
import com.example.mvvmarch.ui.theme.MvvmArchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val notificationViewModel: NotificationViewModel by viewModels()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("FCM", "Notification permission granted")
        } else {
            showToast("Notification permission required")
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MvvmArchTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(text = "Products Category")
                        },
                        actions = {
                            IconButton(onClick = {
                                Toast.makeText(applicationContext, "Shopping Cart Clicked", Toast.LENGTH_SHORT).show()
                            }) {
                                BadgedBox(badge = { Badge { Text("2") } }) {
                                    Icon(
                                        imageVector = Icons.Filled.ShoppingCart,
                                        contentDescription = "Shopping Cart",
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        },
                        modifier = Modifier.background(Color.LightGray)
                            .padding(end = 8.dp),
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.LightGray,
                            titleContentColor = Color.Black,
                            actionIconContentColor = Color.Black
                        )
                    )
                }) { innerPadding ->
                    SetupNotificationViewModel()
                    val navController = rememberNavController()
                    AppNavHost(navController, Modifier.padding(innerPadding))
                }
            }
        }

        requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
    }

    @Composable
    fun AppNavHost(navController: NavHostController, modifier: Modifier) {
        NavHost(navController = navController, startDestination = "category") {
            composable("category") {
                CategoryScreen(navController = navController, modifier = modifier)
            }
            composable(
                "productList/{category}",
                arguments = listOf(
                    navArgument("category") {
                        type = NavType.StringType
                    }
                )
            ) {
                val category = it.arguments?.getString("category") ?: ""
                ProductListScreen(navController = navController, modifier = modifier, category = category) }

            composable("productDetails") {
                ProductDetailsScreen(navController = navController, modifier = modifier)
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
    }

    private fun SetupNotificationViewModel() {
        notificationViewModel.fcmToken.observe(this) {
            Log.d("FCM", "FCM Token: $it")
        }
        notificationViewModel.getFCMToken()
        notificationViewModel.subscribeToTopic("all")
    }

}


/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MvvmArchTheme {
        CategoryScreen()
    }
}*/
