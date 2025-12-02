package com.example.flowersshop.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.flowersshop.ui.components.BottomNav
import com.example.flowersshop.ui.login.LoginScreen
import com.example.flowersshop.ui.pages.main.MainScreen
import com.example.flowersshop.ui.onb.OnbScreen
import com.example.flowersshop.ui.pages.bouquet.BouquetScreen
import com.example.flowersshop.ui.pages.fav.FavouriteScreen
import com.example.flowersshop.ui.pages.profile.ProfileScreen
import com.example.flowersshop.ui.splash.SplashScreen
import com.example.flowersshop.ui.theme.FlowersShopTheme
import dagger.hilt.android.AndroidEntryPoint

sealed class Route(val route: String) {
    data object Main : Route("main")
    data object Splash : Route("splash")
    data object Favourites : Route("favourite")
    data object Profile : Route("profile")
    data object Onb : Route("onb")
    data object Bouquet : Route("bouquet")
    data object Login : Route("login")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route

            val notBottomBars = listOf(
                "splash",
                "onb",
                "login",

                )
            val cartCount = remember { mutableStateOf(1) }
            FlowersShopTheme(dynamicColor = false) {
                Scaffold(
                    bottomBar = {
                        if (currentRoute !in notBottomBars) {
                            BottomNav(
                                navController,
                                modifier = Modifier
                                    .navigationBarsPadding()
                                    .fillMaxWidth()
                                    .height(100.dp),
                                //.background(Color.Yellow)//.padding(horizontal = 25.dp)
                                cart = cartCount
                            )
                        }

                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.Bouquet.route,
                        modifier = Modifier.Companion
                            .padding(innerPadding)
                    ) {
                        composable(Route.Main.route) {
                            MainScreen(navController, cartCount)
                        }
                        composable(Route.Favourites.route) {
                            FavouriteScreen(navController)
                        }
                        composable(Route.Profile.route) {
                            ProfileScreen(navController)
                        }
                        composable(Route.Bouquet.route) {
                            BouquetScreen(navController)
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