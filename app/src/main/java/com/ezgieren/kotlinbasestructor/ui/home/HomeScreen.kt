package com.ezgieren.kotlinbasestructor.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ezgieren.kotlinbasestructor.util.Resource
import com.ezgieren.kotlinbasestructor.viewmodel.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {
    val dataState by viewModel.dataState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Home Screen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (val state = dataState) {
                is Resource.Loading -> {
                    CircularProgressIndicator() // Yükleniyor göstergesi
                }
                is Resource.Success -> {
                    state.data?.forEach { item ->
                        Text(text = "ID: ${item.id}, Name: ${item.name}")
                    }
                }
                is Resource.Error -> {
                    Text(text = "Error: ${state.message}")
                    Text(text = "Details: ${state.detailedMessage}")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.fetchData("testParam") }) {
                Text("Fetch Data")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}