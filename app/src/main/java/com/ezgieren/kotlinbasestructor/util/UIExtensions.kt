package com.ezgieren.kotlinbasestructor.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

object UIExtensions {

    object UiConstants {
        val PaddingSmall = 8.dp
        val PaddingNormal = 16.dp
        val PaddingLarge = 24.dp
        val CornerRadius = 12.dp
    }

    // Custom Button
    @Composable
    fun CustomButton(
        text: String,
        onClick: () -> Unit,
        backgroundColor: Color = MaterialTheme.colorScheme.primary,
        textColor: Color = MaterialTheme.colorScheme.onPrimary
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
        ) {
            Text(
                text = text,
                color = textColor,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    // Custom Image Loader
    @Composable
    fun CustomImage(
        url: String,
        modifier: Modifier = Modifier,
        cornerRadius: Dp = 12.dp
    ) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(cornerRadius))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }

    // Loading Indicator
    @Composable
    fun LoadingIndicator() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}