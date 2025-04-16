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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mvvmarch.domain.model.Category
import com.example.mvvmarch.presentation.ui.screen.CategoryScreen
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
                Scaffold() { innerPadding ->
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
