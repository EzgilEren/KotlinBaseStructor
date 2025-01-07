package com.ezgieren.kotlinbasestructor.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ezgieren.kotlinbasestructor.ui.components.ReusableButton
import com.ezgieren.kotlinbasestructor.ui.components.ReusableText
import com.ezgieren.kotlinbasestructor.util.Constants
import com.ezgieren.kotlinbasestructor.util.Resource
import com.ezgieren.kotlinbasestructor.viewmodel.exampleApi.ExampleViewModel

@Composable
fun HomeScreen(
    viewModel: ExampleViewModel = hiltViewModel(),
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
        // Ekran Başlığı
        ReusableText(
            text = Constants.HOME_SCREEN_TITLE,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (val state = dataState) {
            is Resource.Loading -> {
                ReusableText(text = Constants.LOADING_MESSAGE, textAlign = TextAlign.Center)
            }

            is Resource.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.data ?: emptyList()) { example ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            ReusableText(
                                text = "${Constants.NAME_LABEL}: ${example.name}",
                                fontSize = 16.sp
                            )
                            ReusableText(
                                text = "${Constants.DESCRIPTION_LABEL}: ${example.description}",
                                fontSize = 14.sp,
                                textAlign = TextAlign.Start
                            )
                            ReusableButton(
                                text = "Post Title: ${example.description}",
                                onClick = {
                                    navController.navigate(Constants.DETAIL_ROUTE.replace("{userId}", example.id.toString()))
                                }
                            )
                        }
                    }
                }
            }

            is Resource.Error -> {
                ReusableText(
                    text = Constants.ERROR_MESSAGE,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                ReusableText(
                    text = state.message ?: "",
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Veri Çekme Butonu
        ReusableButton(
            text = Constants.FETCH_DATA_BUTTON,
            onClick = { viewModel.fetchExampleData(dataState.toString()) } // buradan emin değilim param ne olacak
        )
    }
}