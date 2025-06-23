package dev.joppien.swapishowcase.ui.features.movies.details

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.joppien.swapishowcase.R
import dev.joppien.swapishowcase.ui.components.EntryListRow
import dev.joppien.swapishowcase.ui.components.EntryRow
import dev.joppien.swapishowcase.ui.theme.SWAPIAppTheme
import dev.joppien.swapishowcase.ui.theme.spacing
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

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
        topBar = {
            LargeTopAppBar(title = {
                Text(
                    text = if (uiState is MovieDetailsState)
                        uiState.title
                    else
                        stringResource(R.string.feature_movies_title),
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
                is MovieDetailsLoadingState -> {
                    CircularProgressIndicator()
                }

                is MovieDetailsErrorState -> {
                    Text("Something went wrong!")
                }

                is MovieDetailsState -> {
                    val movie = uiState
                    val dateFormatter = remember {
                        DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                    }

                    Text(
                        modifier = Modifier.padding(bottom = MaterialTheme.spacing.spacingEntryColumn),
                        text = movie.openingCrawl
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacingEntryColumn)) {
                        EntryRow(
                            labelString = "Episode Number",
                            entryString = movie.episodeId.toString()
                        )
                        movie.releaseDate?.let { date ->
                            EntryRow(
                                labelString = "Release Date",
                                entryString = date.format(dateFormatter)
                            )
                        }
                        EntryRow(labelString = "Director", entryString = movie.director)
                        EntryListRow(labelString = "Producers", entryList = movie.producers)
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
    SWAPIAppTheme {
        MovieScreenContent(
            MovieDetailsState(
                id = 1,
                title = "Title",
                episodeId = 1,
                releaseDate = LocalDate.of(1977, 5, 25),
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
    SWAPIAppTheme {
        MovieScreenContent(
            MovieDetailsState(
                id = 1,
                title = "Title",
                episodeId = 1,
                releaseDate = LocalDate.of(1977, 5, 25),
                director = "Director",
                producers = listOf("Producer 1", "Producer 2"),
                openingCrawl = "Opening Crawl",
            )
        )
    }
}