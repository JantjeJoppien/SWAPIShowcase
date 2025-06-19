package dev.joppien.swapishowcase.ui.features.transports.vehicle

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.joppien.swapishowcase.R
import dev.joppien.swapishowcase.ui.theme.MainTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleDetailsScreen(
    viewModel: VehicleDetailsViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    VehicleScreenContent(uiState = uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleScreenContent(
    uiState: VehicleUiState
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = if (uiState is VehicleState)
                        uiState.name
                    else
                        stringResource(R.string.feature_transport_title)
                )
            })
        }
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
                }

                is VehicleState -> {
                    val vehicle = uiState
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
    MainTheme {
        VehicleScreenContent(
            uiState = VehicleState(
                name = "Name",
                model = "Model",
                vehicleClass = "Vehicle Class",
                manufacturer = "Manufacturer",
                costInCredits = "Cost in Credits",
                length = "Length",
                maxAtmospheringSpeed = "Max Atmosphering Speed",
                cargoCapacity = "Cargo Capacity",
                consumables = "Consumables",
            )
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VehicleDetailsScreenDarkPreview() {
    MainTheme {
        VehicleScreenContent(
            uiState = VehicleState(
                name = "Name",
                model = "Model",
                vehicleClass = "Vehicle Class",
                manufacturer = "Manufacturer",
                costInCredits = "Cost in Credits",
                length = "Length",
                maxAtmospheringSpeed = "Max Atmosphering Speed",
                cargoCapacity = "Cargo Capacity",
                consumables = "Consumables",
            )
        )
    }
}