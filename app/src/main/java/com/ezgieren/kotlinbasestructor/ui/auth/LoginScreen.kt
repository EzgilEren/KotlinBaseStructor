package com.ezgieren.kotlinbasestructor.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ezgieren.kotlinbasestructor.util.Constants

@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = Constants.LOGIN_SCREEN_TITLE)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(Constants.HOME_ROUTE) {
                popUpTo(Constants.LOGIN_ROUTE) { inclusive = true }
            }
        }) {
            Text(text = Constants.LOGIN_BUTTON_TEXT)
        }
    }
}