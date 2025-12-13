package com.example.flowersshop.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.flowersshop.domain.model.BouquetModel
import com.example.flowersshop.domain.model.ItemModel
import com.example.flowersshop.ui.components.BottomNav
import com.example.flowersshop.ui.login.LoginScreen
import com.example.flowersshop.ui.pages.main.MainScreen
import com.example.flowersshop.ui.onb.OnbScreen
import com.example.flowersshop.ui.pages.bouquet.BouquetScreen
import com.example.flowersshop.ui.pages.fav.FavoriteScreen
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
            val favorites = mutableListOf<BouquetModel>(
                BouquetModel("1", "https://usbtrvwvcopzwhjczmzh.supabase.co/storage/v1/object/public/bouquets/bouquet1.png", "Букет 1", 1000),
                BouquetModel("2", "https://usbtrvwvcopzwhjczmzh.supabase.co/storage/v1/object/public/bouquets/bouquet2.png", "Букет 2", 2000),
                BouquetModel("3", "https://usbtrvwvcopzwhjczmzh.supabase.co/storage/v1/object/public/bouquets/bouquet3.png", "Букет 3", 3000),
                BouquetModel("4", "https://usbtrvwvcopzwhjczmzh.supabase.co/storage/v1/object/public/bouquets/bouquet4.png", "Букет 4", 4000),
            )
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
                        startDestination = Route.Splash.route,
                        modifier = Modifier.Companion
                            .padding(innerPadding)
                    ) {
                        composable(Route.Main.route) {
                            MainScreen(navController, cartCount)
                        }
                        composable(Route.Favourites.route) {
                            FavoriteScreen(navController, favorites)
                        }
                        composable(Route.Profile.route) {
                            ProfileScreen(navController)
                        }
                        composable(Route.Bouquet.route) {
                            BouquetScreen(navController, cart = cartCount)
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