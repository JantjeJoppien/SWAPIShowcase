package dev.joppien.swapishowcase.ui.features.transports.vehicle

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.joppien.swapishowcase.R
import dev.joppien.swapishowcase.ui.components.EntryRow
import dev.joppien.swapishowcase.ui.theme.SWAPIAppTheme
import dev.joppien.swapishowcase.ui.theme.spacing

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
            LargeTopAppBar(title = {
                Text(
                    text = if (uiState is VehicleState)
                        uiState.name
                    else
                        stringResource(R.string.feature_transport_title),
                    style = MaterialTheme.typography.headlineLarge,
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

                    Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacingEntryColumn)) {
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_model_label),
                            entryString = vehicle.model,
                        )
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_manufacturer_label),
                            entryString = vehicle.manufacturer,
                        )
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_class_label),
                            entryString = vehicle.vehicleClass,
                        )
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_costInCredits_label),
                            entryString = vehicle.costInCredits,
                        )
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_length_label),
                            entryString = vehicle.length,
                        )
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_maxAtmospheringSpeed_label),
                            entryString = vehicle.maxAtmospheringSpeed,
                        )
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_cargoCapacity_label),
                            entryString = vehicle.cargoCapacity,
                        )
                        EntryRow(
                            labelString = stringResource(
                                R.string.feature_transport_consumables_label,
                                stringResource(R.string.feature_transport_vehicle_label),
                            ),
                            entryString = vehicle.consumables,
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun VehicleDetailsScreenPreview() {
    SWAPIAppTheme {
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
    SWAPIAppTheme {
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