package dev.joppien.swapishowcase.ui.features.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import dev.joppien.swapishowcase.R
import dev.joppien.swapishowcase.ui.components.NavigationButton
import dev.joppien.swapishowcase.ui.navigation.AppDestinations
import dev.joppien.swapishowcase.ui.theme.SWAPIAppTheme
import dev.joppien.swapishowcase.ui.theme.spacing
import dev.joppien.swapishowcase.ui.util.rememberPreviewNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(R.string.feature_home_title),
                    style = MaterialTheme.typography.headlineMedium,
                )
            })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacingButtonsColumn),
            ) {
                NavigationButton(
                    textId = R.string.feature_movies_title,
                    onClick = { navController.navigate(AppDestinations.MOVIE_LIST_ROUTE) }
                )
                Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacingButtonsRow)) {
                    NavigationButton(
                        textId = R.string.feature_people_title,
                        onClick = { navController.navigate(AppDestinations.PEOPLE_LIST_ROUTE) }
                    )
                    NavigationButton(
                        textId = R.string.feature_transport_title,
                        onClick = { navController.navigate(AppDestinations.TRANSPORT_LIST_ROUTE) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val previewNavController = rememberPreviewNavController()
    SWAPIAppTheme {
        HomeScreen(
            navController = previewNavController,
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenDarkPreview() {
    val previewNavController = rememberPreviewNavController()
    SWAPIAppTheme {
        HomeScreen(
            navController = previewNavController
        )
    }
}