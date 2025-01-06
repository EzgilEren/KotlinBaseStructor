package com.ezgieren.kotlinbasestructor.ui.splash

import android.window.SplashScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ezgieren.kotlinbasestructor.util.Constants
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    // Splash API integrations
    LaunchedEffect(Unit) {
        delay(2000) // Simüle edilmiş yükleme süresi
        val isUserLoggedIn = false // Gerçek oturum kontrolü yapılacak
        if (isUserLoggedIn) {
            navController.navigate(Constants.HOME_ROUTE) {
                popUpTo(Constants.SPLASH_ROUTE) { inclusive = true }
            }
        } else {
            navController.navigate(Constants.LOGIN_ROUTE) {
                popUpTo(Constants.SPLASH_ROUTE) { inclusive = true }
            }
        }
    }
    // Splash ekranı görseli
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = Constants.SPLASH_SCREEN_TITLE)
        CircularProgressIndicator()
    }
}