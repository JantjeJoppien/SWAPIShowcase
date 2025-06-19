package dev.joppien.swapishowcase.ui.features.movies.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.joppien.swapishowcase.R
import dev.joppien.swapishowcase.ui.navigation.AppScreens
import dev.joppien.swapishowcase.ui.theme.MainTheme
import dev.joppien.swapishowcase.ui.util.rememberPreviewNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.feature_movies_title)) }) }
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
                is MovieListLoadingState -> {
                    CircularProgressIndicator()
                }

                is MovieListErrorState -> {
                    Text("Something went wrong!")
                    Button(onClick = { viewModel.refreshData() }) {
                        Text(text = stringResource(R.string.general_button_refresh))
                    }
                }

                is MovieListState -> {
                    val movies = (uiState as MovieListState).movies
                    movies.forEach { movie ->
                        Card(
                            modifier = Modifier.clickable {
                                navController.navigate("${AppScreens.MOVIE_DETAILS_SCREEN}/${movie.id}")
                            }
                        ) {
                            Text(movie.title)
                            Text(movie.episodeId.toString())
                            Text(movie.releaseDate)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListScreenPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        MovieListScreen(navController = previewNavController)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovieListScreenDarkPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        MovieListScreen(navController = previewNavController)
    }
}