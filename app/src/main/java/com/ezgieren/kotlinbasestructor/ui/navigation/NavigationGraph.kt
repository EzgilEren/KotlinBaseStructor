package com.ezgieren.kotlinbasestructor.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ezgieren.kotlinbasestructor.ui.auth.LoginScreen
import com.ezgieren.kotlinbasestructor.ui.details.DetailsScreen
import com.ezgieren.kotlinbasestructor.ui.home.HomeScreen
import com.ezgieren.kotlinbasestructor.ui.post.PostScreen
import com.ezgieren.kotlinbasestructor.ui.splash.SplashScreen
import com.ezgieren.kotlinbasestructor.util.Constants

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Constants.SPLASH_ROUTE) {
        composable(Constants.SPLASH_ROUTE) {
            SplashScreen(navController = navController)
        }
        composable(Constants.LOGIN_ROUTE) {
            LoginScreen(navController = navController)
        }
        composable(Constants.HOME_ROUTE) {
            HomeScreen(navController = navController)
        }
        composable(Constants.POST_ROUTE) {
            PostScreen(navController = navController)
        }
        composable(Constants.DETAIL_ROUTE) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: Constants.DEFAULT_USER_ID
            DetailsScreen(navController = navController, userId = userId)
        }
    }
}