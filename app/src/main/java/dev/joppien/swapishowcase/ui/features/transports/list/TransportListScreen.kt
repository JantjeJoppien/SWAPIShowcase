package dev.joppien.swapishowcase.ui.features.transports.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import dev.joppien.swapishowcase.R
import dev.joppien.swapishowcase.ui.components.IconRow
import dev.joppien.swapishowcase.ui.navigation.AppScreens
import dev.joppien.swapishowcase.ui.theme.SWAPIAppTheme
import dev.joppien.swapishowcase.ui.theme.spacing
import dev.joppien.swapishowcase.ui.util.rememberPreviewNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransportListScreen(
    navController: NavController,
    viewModel: TransportListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(R.string.feature_transport_title),
                    style = MaterialTheme.typography.headlineMedium,
                )
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(
                    start = spacing.paddingScreenSides,
                    end = spacing.paddingScreenSides,
                    top = spacing.paddingScreenTopBottom,
                    bottom = spacing.paddingScreenTopBottom,
                )
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            when (uiState) {
                is TransportListLoadingState -> {
                    CircularProgressIndicator()
                }

                is TransportListErrorState -> {
                    Text("Something went wrong!")
                    Button(onClick = { viewModel.refreshData() }) {
                        Text("Retry")
                    }
                }

                is TransportListState -> {
                    val vehicles = (uiState as TransportListState).vehicles

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = spacing.gridMinSize),
                        verticalArrangement = Arrangement.spacedBy(spacing.spacingCardGrid),
                        horizontalArrangement = Arrangement.spacedBy(spacing.spacingCardGrid),
                    ) {
                        items(
                            count = vehicles.size,
                            key = { index -> vehicles[index].id }
                        ) { index ->
                            val vehicle = vehicles[index]

                            Card(
                                modifier = Modifier
                                    .clickable {
                                        when (vehicle.type) {
                                            TransportType.VEHICLE -> navController.navigate("${AppScreens.VEHICLE_DETAILS_SCREEN}/${vehicle.id}")
                                            TransportType.STARSHIP -> navController.navigate("${AppScreens.STARSHIP_DETAILS_SCREEN}/${vehicle.id}")
                                        }
                                    }
                            ) {
                                Column(
                                    modifier = Modifier.padding(
                                        start = spacing.paddingCardSides,
                                        end = spacing.paddingCardSides,
                                        top = spacing.paddingCardTopBottom,
                                        bottom = spacing.paddingCardTopBottom,
                                    ),
                                    verticalArrangement = Arrangement.spacedBy(spacing.spacingCard),
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = vehicle.name,
                                        style = MaterialTheme.typography.titleMedium,
                                        textAlign = TextAlign.Center,
                                    )
                                    Text(
                                        text = vehicle.model,
                                        style = MaterialTheme.typography.bodySmall,
                                    )
                                    IconRow(
                                        iconId = R.drawable.ic_credit,
                                        text = vehicle.costInCredits,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransportListScreenPreview() {
    val previewNavController = rememberPreviewNavController()
    SWAPIAppTheme {
        TransportListScreen(navController = previewNavController)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TransportListScreenDarkPreview() {
    val previewNavController = rememberPreviewNavController()
    SWAPIAppTheme {
        TransportListScreen(navController = previewNavController)
    }
}