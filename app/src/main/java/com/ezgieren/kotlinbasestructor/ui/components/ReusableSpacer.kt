package com.ezgieren.kotlinbasestructor.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ReusableSpacer(
    height: Dp = 16.dp, // Default height
    width: Dp = 0.dp    // Default width
) {
    Spacer(modifier = Modifier.height(height).width(width))
}