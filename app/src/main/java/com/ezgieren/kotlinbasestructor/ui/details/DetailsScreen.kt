package com.ezgieren.kotlinbasestructor.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ezgieren.kotlinbasestructor.ui.components.ReusableSpacer
import com.ezgieren.kotlinbasestructor.ui.components.ReusableText
import com.ezgieren.kotlinbasestructor.util.Constants

@Composable
fun DetailsScreen(navController: NavHostController, userId: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ReusableText(
            text = Constants.DETAILS_SCREEN_TITLE,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            textColor = androidx.compose.ui.graphics.Color.Red
        )
        ReusableSpacer(height = 24.dp)
        ReusableText(
            text = "${Constants.DETAILS_USER_ID_PREFIX} $userId",
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )
    }
}