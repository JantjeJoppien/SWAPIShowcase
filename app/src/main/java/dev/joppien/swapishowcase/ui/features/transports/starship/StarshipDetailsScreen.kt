package dev.joppien.swapishowcase.ui.features.transports.starship

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
fun StarshipDetailsScreen(
    navController: NavController,
    starshipId: Int,
    viewModel: StarshipDetailsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Transports") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (uiState) {
                is StarshipLoadingState -> {
                    CircularProgressIndicator()
                }

                is StarshipErrorState -> {
                    Text("Something went wrong!")
                    Button(onClick = { viewModel.refreshData() }) {
                        Text("Retry")
                    }
                }

                is StarshipState -> {
                    val starship = (uiState as StarshipState)
                    Text(starship.name)
                    Text(starship.model)
                    Text(starship.starshipClass)
                    Text(starship.manufacturer)
                    Text(starship.costInCredits)
                    Text(starship.length)
                    Text(starship.maxAtmospheringSpeed)
                    Text(starship.hyperdriveRating)
                    Text(starship.mglt)
                    Text(starship.cargoCapacity)
                    Text(starship.consumables)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StarshipDetailsScreenPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        StarshipDetailsScreen(navController = previewNavController, starshipId = 1)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StarshipDetailsScreenDarkPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        StarshipDetailsScreen(navController = previewNavController, starshipId = 1)
    }
}