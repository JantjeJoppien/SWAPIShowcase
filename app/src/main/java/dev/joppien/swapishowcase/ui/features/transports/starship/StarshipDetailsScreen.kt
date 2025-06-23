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
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_model_label),
                            entryString = starship.model,
                        )
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_manufacturer_label),
                            entryString = starship.manufacturer,
                        )
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_class_label),
                            entryString = starship.starshipClass,
                        )
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_costInCredits_label),
                            entryString = starship.costInCredits,
                        )
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_length_label),
                            entryString = starship.length,
                        )
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_maxAtmospheringSpeed_label),
                            entryString = starship.maxAtmospheringSpeed,
                        )
                        EntryRow(
                            labelString = stringResource(
                                R.string.feature_transport_mglt_label,
                                stringResource(R.string.feature_transport_starship_label),
                            ),
                            entryString = starship.mglt,
                        )
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_hyperdriveRating_label),
                            entryString = starship.hyperdriveRating,
                        )
                        EntryRow(
                            labelString = stringResource(R.string.feature_transport_cargoCapacity_label),
                            entryString = starship.cargoCapacity,
                        )
                        EntryRow(
                            labelString = stringResource(
                                R.string.feature_transport_consumables_label,
                                stringResource(R.string.feature_transport_starship_label),
                            ),
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