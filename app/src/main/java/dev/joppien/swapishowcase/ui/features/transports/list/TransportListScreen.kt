package dev.joppien.swapishowcase.ui.features.transports.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.joppien.swapishowcase.R
import dev.joppien.swapishowcase.ui.navigation.AppScreens
import dev.joppien.swapishowcase.ui.theme.MainTheme
import dev.joppien.swapishowcase.ui.util.rememberPreviewNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransportListScreen(
    navController: NavController,
    viewModel: TransportListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.feature_transport_title)) }) }
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
                    vehicles.forEach { vehicle ->
                        Card(
                            modifier = Modifier.clickable {
                                when (vehicle.type) {
                                    TransportType.VEHICLE -> navController.navigate("${AppScreens.VEHICLE_DETAILS_SCREEN}/${vehicle.id}")
                                    TransportType.STARSHIP -> navController.navigate("${AppScreens.STARSHIP_DETAILS_SCREEN}/${vehicle.id}")
                                }
                            }
                        ) {
                            Text(vehicle.name)
                            Text(vehicle.model)
                            Text(vehicle.costInCredits)
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
    MainTheme {
        TransportListScreen(navController = previewNavController)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TransportListScreenDarkPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        TransportListScreen(navController = previewNavController)
    }
}