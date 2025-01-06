package com.ezgieren.kotlinbasestructor.ui.post

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ezgieren.kotlinbasestructor.ui.components.ReusableButton
import com.ezgieren.kotlinbasestructor.ui.components.ReusableText
import com.ezgieren.kotlinbasestructor.util.Constants
import com.ezgieren.kotlinbasestructor.util.Resource
import com.ezgieren.kotlinbasestructor.viewmodel.post.PostViewModel

@Composable
fun PostScreen(navController: NavController, viewModel: PostViewModel = hiltViewModel()) {
    BackHandler {
        navController.popBackStack()
    }
    val dataState by viewModel.dataState.collectAsState()

    Column {
        when (val state = dataState) {
            is Resource.Loading -> ReusableText(text = Constants.LOADING_MESSAGE)
            is Resource.Success -> state.data?.forEach {
                ReusableText(text = it.title)
                Spacer(modifier = Modifier.height(16.dp))
                ReusableButton(text = Constants.BACK_BUTTON_TEXT, onClick = { navController.popBackStack() })
            }
            is Resource.Error -> ReusableText(text = "${Constants.ERROR_MESSAGE} ${state.message}")
        }
    }
}