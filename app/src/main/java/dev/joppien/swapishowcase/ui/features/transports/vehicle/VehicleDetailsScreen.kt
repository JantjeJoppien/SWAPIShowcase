package dev.joppien.swapishowcase.ui.features.transports.vehicle

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
fun VehicleDetailsScreen(
    navController: NavController,
    vehicleId: Int,
    viewModel: VehicleDetailsViewModel = hiltViewModel(),
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
                is VehicleLoadingState -> {
                    CircularProgressIndicator()
                }

                is VehicleErrorState -> {
                    Text("Something went wrong!")
                    Button(onClick = { viewModel.refreshData() }) {
                        Text("Retry")
                    }
                }

                is VehicleState -> {
                    val vehicle = (uiState as VehicleState)
                    Text(vehicle.name)
                    Text(vehicle.model)
                    Text(vehicle.vehicleClass)
                    Text(vehicle.manufacturer)
                    Text(vehicle.costInCredits)
                    Text(vehicle.length)
                    Text(vehicle.maxAtmospheringSpeed)
                    Text(vehicle.cargoCapacity)
                    Text(vehicle.consumables)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VehicleDetailsScreenPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        VehicleDetailsScreen(navController = previewNavController, vehicleId = 1)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VehicleDetailsScreenDarkPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        VehicleDetailsScreen(navController = previewNavController, vehicleId = 1)
    }
}