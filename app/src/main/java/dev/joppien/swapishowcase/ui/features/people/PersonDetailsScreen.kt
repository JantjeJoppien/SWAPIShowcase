package dev.joppien.swapishowcase.ui.features.people

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.joppien.swapishowcase.ui.theme.MainTheme
import dev.joppien.swapishowcase.ui.util.rememberPreviewNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonDetailsScreen(
    navController: NavController,
    viewModel: PersonDetailsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("People") }) }
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
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonDetailsScreenPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        PersonDetailsScreen(navController = previewNavController)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PersonDetailsScreenDarkPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        PersonDetailsScreen(navController = previewNavController)
    }
}