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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flowersshop.domain.model.BouquetModel
import com.example.flowersshop.ui.bouquet.BouquetCardScreen
import com.example.flowersshop.ui.components.BottomNav
import com.example.flowersshop.ui.login.LoginScreen
import com.example.flowersshop.ui.onb.OnbScreen
import com.example.flowersshop.ui.pages.bouquet.BouquetScreen
import com.example.flowersshop.ui.pages.cart.CartRepository
import com.example.flowersshop.ui.pages.cart.CartScreen
import com.example.flowersshop.ui.pages.cart.OrderScreen
import com.example.flowersshop.ui.pages.fav.FavoriteScreen
import com.example.flowersshop.ui.pages.main.MainScreen
import com.example.flowersshop.ui.signup.SignupScreen
import com.example.flowersshop.ui.splash.SplashScreen
import com.example.flowersshop.ui.theme.FlowersShopTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

sealed class Route(val route: String) {
    data object Main : Route("main")
    data object Splash : Route("splash")
    data object Favourites : Route("favourite")
    data object Cart : Route("cart")
    data object Order : Route("order")
    data object Onb : Route("onb")
    data object Bouquet : Route("bouquet")
    data object Login : Route("login")
    data object Signup : Route("signup")
    data object BouquetCard : Route("bouquetCard/{bouquetId}") {
        fun createRoute(bouquetId: String) = "bouquetCard/$bouquetId"
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var cartRepository: CartRepository
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
                "bouquetCard",
                "order",
                "signup"
            )
            val hideBottomBar = notBottomBars.any { route ->
                currentRoute?.startsWith(route) == true
            }
            FlowersShopTheme(dynamicColor = false) {
                Scaffold(
                    bottomBar = {
                        if (!hideBottomBar) {
                            BottomNav(
                                navController,
                                cartRepository = cartRepository,
                                modifier = Modifier
                                    .navigationBarsPadding()
                                    .fillMaxWidth()
                                    .height(100.dp),
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
                            MainScreen(navController)
                        }
                        composable(Route.Favourites.route) {
                            FavoriteScreen(navController)
                        }
                        composable(Route.Cart.route) {
                            CartScreen(navController)
                        }
                        composable(Route.Order.route) {
                            OrderScreen(navController)
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
                        composable(Route.Signup.route) {
                            SignupScreen(navController)
                        }
                        composable(
                            route = Route.BouquetCard.route,
                            arguments = listOf(
                                navArgument("bouquetId") {
                                    type = NavType.StringType
                                }
                            )) {
                            BouquetCardScreen(navController)
                        }
                    }
                }
            }
        }
    }
}