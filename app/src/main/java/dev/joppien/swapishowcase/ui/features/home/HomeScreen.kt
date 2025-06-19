package dev.joppien.swapishowcase.ui.features.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.joppien.swapishowcase.R
import dev.joppien.swapishowcase.ui.navigation.AppDestinations
import dev.joppien.swapishowcase.ui.theme.MainTheme
import dev.joppien.swapishowcase.ui.util.rememberPreviewNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.feature_home_title)) }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Button(onClick = { navController.navigate(AppDestinations.MOVIE_LIST_ROUTE) }) {
                Text(text = stringResource(R.string.feature_movies_title))
            }
            Button(onClick = { navController.navigate(AppDestinations.PEOPLE_LIST_ROUTE) }) {
                Text(text = stringResource(R.string.feature_people_tile))
            }
            Button(onClick = { navController.navigate(AppDestinations.TRANSPORT_LIST_ROUTE) }) {
                Text(text = stringResource(R.string.feature_transport_title))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        HomeScreen(
            navController = previewNavController,
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenDarkPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        HomeScreen(
            navController = previewNavController
        )
    }
}