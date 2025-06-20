package dev.joppien.swapishowcase.ui.features.movies.details

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.joppien.swapishowcase.ui.theme.MainTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    MovieScreenContent(uiState = uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreenContent(
    uiState: MovieDetailsUiState,
) {
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
                }

                is MovieDetailsState -> {
                    val movie = uiState
                    Text(movie.title)
                    Text(movie.episodeId.toString())
                    Text(movie.releaseDate)
                    Text(movie.director)
                    Text(movie.producers.joinToString())
                    Text(movie.openingCrawl)
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
    MainTheme {
        MovieScreenContent(
            MovieDetailsState(
                id = 1,
                title = "Title",
                episodeId = 1,
                releaseDate = "Release Date",
                director = "Director",
                producers = listOf("Producer 1", "Producer 2"),
                openingCrawl = "Opening Crawl",
            )
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovieDetailsScreenDarkPreview() {
    MainTheme {
        MovieScreenContent(
            MovieDetailsState(
                id = 1,
                title = "Title",
                episodeId = 1,
                releaseDate = "Release Date",
                director = "Director",
                producers = listOf("Producer 1", "Producer 2"),
                openingCrawl = "Opening Crawl",
            )
        )
    }
}