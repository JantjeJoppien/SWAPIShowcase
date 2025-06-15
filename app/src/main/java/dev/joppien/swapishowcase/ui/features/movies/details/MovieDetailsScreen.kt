package dev.joppien.swapishowcase.ui.features.movies.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.joppien.swapishowcase.ui.theme.MainTheme
import dev.joppien.swapishowcase.ui.util.rememberPreviewNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movieId: Int,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Movies") }) }
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
                is MovieDetailsLoadingState -> {
                    CircularProgressIndicator()
                }

                is MovieDetailsErrorState -> {
                    Text("Something went wrong!")
                    Button(onClick = { viewModel.refreshData() }) {
                        Text("Retry")
                    }
                }

                is MovieDetailsState -> {
                    val movie = (uiState as MovieDetailsState)
                    Text(movie.title)
                    Text(movie.episodeId.toString())
                    Text(movie.releaseDate)
                    Text(movie.director)
                    Text(movie.producer.joinToString())
                    Text(movie.openingCrawl)
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        MovieDetailsScreen(navController = previewNavController, movieId = 1)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovieDetailsScreenDarkPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        MovieDetailsScreen(navController = previewNavController, movieId = 1)
    }
}