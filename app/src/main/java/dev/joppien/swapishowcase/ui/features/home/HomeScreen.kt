package dev.joppien.swapishowcase.ui.features.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.joppien.swapishowcase.ui.theme.MainTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToMovieList: () -> Unit,
    onNavigateToPeopleList: () -> Unit,
    onNavigateToTransportList: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Home") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else if (uiState.error != null) {
                Text("Error: ${uiState.error}")
                Button(onClick = { viewModel.refreshData() }) {
                    Text("Retry")
                }
            } else {
                Text(uiState.data)
                Button(onClick = onNavigateToMovieList) {
                    Text("View All Movies")
                }
                Button(onClick = onNavigateToPeopleList) {
                    Text("View All People")
                }
                Button(onClick = onNavigateToTransportList) {
                    Text("Hangar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun YourFeatureScreenPreview() {
    MainTheme {
        HomeScreen(
            onNavigateToMovieList = {},
            onNavigateToPeopleList = {},
            onNavigateToTransportList = {},
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun YourFeatureScreenDarkPreview() {
    MainTheme {
        HomeScreen(
            onNavigateToMovieList = {},
            onNavigateToPeopleList = {},
            onNavigateToTransportList = {},
        )
    }
}