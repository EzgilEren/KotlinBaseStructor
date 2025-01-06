package com.ezgieren.kotlinbasestructor.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ezgieren.kotlinbasestructor.ui.components.ReusableButton
import com.ezgieren.kotlinbasestructor.ui.components.ReusableSpacer
import com.ezgieren.kotlinbasestructor.ui.components.ReusableText
import com.ezgieren.kotlinbasestructor.util.Constants
import com.ezgieren.kotlinbasestructor.util.Resource
import com.ezgieren.kotlinbasestructor.viewmodel.post.PostViewModel

@Composable
fun HomeScreen(
    viewModel: PostViewModel = hiltViewModel(),
    navController: NavController
) {
    val dataState by viewModel.dataState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ReusableText(text = Constants.HOME_SCREEN_TITLE, fontSize = 20.sp)
        ReusableSpacer(height = 24.dp)

        when (val state = dataState) {
            is Resource.Loading -> {
                ReusableText(text = Constants.LOADING_MESSAGE)
            }

            is Resource.Success -> {
                state.data?.forEach { post ->
                    ReusableButton(
                        text = "Post Title: ${post.title}",
                        onClick = {
                            navController.navigate(Constants.DETAIL_ROUTE.replace("{userId}", post.id.toString()))
                        }
                    )
                    ReusableSpacer(height = 24.dp)
                }
            }

            is Resource.Error -> {
                ReusableText(text = "${Constants.ERROR_MESSAGE} ${state.message}")
                ReusableText(text = "${Constants.DETAILS_MESSAGE} ${state.detailedMessage}")
            }
        }

        ReusableSpacer(height = 16.dp)
        ReusableButton(
            text = Constants.FETCH_DATA_BUTTON,
            onClick = { viewModel.fetchPosts() }
        )
    }
}