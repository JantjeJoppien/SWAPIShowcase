package dev.joppien.swapishowcase.ui.features.transports.starship

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import dev.joppien.swapishowcase.R
import dev.joppien.swapishowcase.ui.components.EntryRow
import dev.joppien.swapishowcase.ui.theme.SWAPIAppTheme
import dev.joppien.swapishowcase.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarshipDetailsScreen(
    viewModel: StarshipDetailsViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    StarshipScreenContent(uiState = uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarshipScreenContent(
    uiState: StarshipUiState,
) {
    Scaffold(
        topBar = {
            LargeTopAppBar(title = {
                Text(
                    text = if (uiState is StarshipState)
                        uiState.name
                    else
                        stringResource(R.string.feature_transport_title),
                    style = MaterialTheme.typography.headlineLarge,
                )
            })
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(
                    start = MaterialTheme.spacing.paddingScreenSides,
                    end = MaterialTheme.spacing.paddingScreenSides,
                    top = MaterialTheme.spacing.paddingScreenTopBottom,
                    bottom = MaterialTheme.spacing.paddingScreenTopBottom,
                )
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (uiState) {
                is StarshipLoadingState -> {
                    CircularProgressIndicator()
                }

                is StarshipErrorState -> {
                    Text("Something went wrong!")
                }

                is StarshipState -> {
                    val starship = uiState

                    Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacingEntryColumn)) {
                        EntryRow(labelString = "Model", entryString = starship.model)
                        EntryRow(labelString = "Manufacturer", entryString = starship.manufacturer)
                        EntryRow(labelString = "Class", entryString = starship.starshipClass)
                        EntryRow(
                            labelString = "Cost in Credits",
                            entryString = starship.costInCredits,
                        )
                        EntryRow(labelString = "Length", entryString = starship.length)
                        EntryRow(
                            labelString = "Max Atmosphering Speed",
                            entryString = starship.maxAtmospheringSpeed,
                        )
                        EntryRow(
                            labelString = "Maximum number of Megalights this starship can travel in a standard hour",
                            entryString = starship.mglt,
                        )
                        EntryRow(
                            labelString = "Hyperdrive Rating",
                            entryString = starship.hyperdriveRating,
                        )
                        EntryRow(
                            labelString = "Cargo Capacity",
                            entryString = starship.cargoCapacity,
                        )
                        EntryRow(
                            labelString = "Maximum time this starship can provide consumables for its entire crew without having to resupply",
                            entryString = starship.consumables,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StarshipDetailsScreenPreview() {
    SWAPIAppTheme {
        StarshipScreenContent(
            uiState = StarshipState(
                name = "Name",
                model = "Model",
                starshipClass = "Starship Class",
                manufacturer = "Manufacturer",
                costInCredits = "Cost in Credits",
                length = "Length",
                maxAtmospheringSpeed = "Max Atmosphering Speed",
                hyperdriveRating = "Hyperdrive Rating",
                mglt = "MGLT",
                cargoCapacity = "Cargo Capacity",
                consumables = "Consumables",
            )
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StarshipDetailsScreenDarkPreview() {
    SWAPIAppTheme {
        StarshipScreenContent(
            uiState = StarshipState(
                name = "Name",
                model = "Model",
                starshipClass = "Starship Class",
                manufacturer = "Manufacturer",
                costInCredits = "Cost in Credits",
                length = "Length",
                maxAtmospheringSpeed = "Max Atmosphering Speed",
                hyperdriveRating = "Hyperdrive Rating",
                mglt = "MGLT",
                cargoCapacity = "Cargo Capacity",
                consumables = "Consumables",
            )
        )
    }
}