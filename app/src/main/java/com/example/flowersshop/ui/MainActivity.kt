package com.example.flowersshop.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flowersshop.ui.cart.CartScreen
import com.example.flowersshop.ui.components.BottomNav
import com.example.flowersshop.ui.login.LoginScreen
import com.example.flowersshop.ui.main.MainScreen
import com.example.flowersshop.ui.onb.OnbScreen
import com.example.flowersshop.ui.profile.ProfileScreen
import com.example.flowersshop.ui.splash.SplashScreen
import com.example.flowersshop.ui.theme.FlowersShopTheme
import dagger.hilt.android.AndroidEntryPoint

sealed class Route(val route: String) {
    data object Main : Route("main")
    data object Splash : Route("splash")
    data object Cart : Route("cart")
    data object Profile : Route("profile")
    data object Onb : Route("onb")
    data object Login : Route("login")
}
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            FlowersShopTheme {
                Scaffold(
                    bottomBar = {
//                        BottomNav(
//                            navController,
//                            modifier = Modifier.navigationBarsPadding().fillMaxWidth()
//                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.Login.route,
                        modifier = Modifier.Companion
                            .padding(innerPadding)
                    ) {
                        composable(Route.Main.route) {

                            MainScreen(navController)
                        }
                        composable(Route.Cart.route) {
                            CartScreen(navController)
                        }
                        composable(Route.Profile.route) {
                            ProfileScreen(navController)
                        }
                        composable(Route.Splash.route) {
                            SplashScreen(navController)
                        }
                        composable(Route.Onb.route) {
                            OnbScreen(navController)
                        }
                        composable(Route.Login.route) {
                            LoginScreen(navController)
                        }
                    }
                }
            }
        }
    }
}