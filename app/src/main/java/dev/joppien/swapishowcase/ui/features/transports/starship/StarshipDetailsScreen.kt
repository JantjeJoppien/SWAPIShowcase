package dev.joppien.swapishowcase.ui.features.transports.starship

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
            TopAppBar(title = {
                Text(
                    text = if (uiState is StarshipState)
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
                is StarshipLoadingState -> {
                    CircularProgressIndicator()
                }

                is StarshipErrorState -> {
                    Text("Something went wrong!")
                }

                is StarshipState -> {
                    val starship = uiState
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
    MainTheme {
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
    MainTheme {
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