package com.ezgieren.kotlinbasestructor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ezgieren.kotlinbasestructor.ui.navigation.NavigationGraph
import com.ezgieren.kotlinbasestructor.ui.theme.KotlinBaseStructorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinBaseStructorTheme {
                val navController = rememberNavController()
                NavigationGraph(navController = navController)
            }
        }
    }
}