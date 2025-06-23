package dev.joppien.swapishowcase.ui.features.movies.details

import android.content.res.Configuration
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.joppien.swapishowcase.R
import dev.joppien.swapishowcase.ui.components.EntryListRow
import dev.joppien.swapishowcase.ui.components.EntryRow
import dev.joppien.swapishowcase.ui.theme.SWAPIAppTheme
import dev.joppien.swapishowcase.ui.theme.StarWarsColor
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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(
                    start = MaterialTheme.spacing.paddingScreenSides,
                    end = MaterialTheme.spacing.paddingScreenSides,
                    top = MaterialTheme.spacing.paddingScreenTopBottom,
                    bottom = MaterialTheme.spacing.paddingScreenTopBottom,
                )
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
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
                    val scrollState = rememberScrollState()


                    InteractiveStarWarsCrawl(
                        text = movie.openingCrawl,
                    )
                    Column(
                        modifier = Modifier.verticalScroll(scrollState),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacingEntryColumn),
                    ) {
                        EntryRow(
                            labelString = stringResource(R.string.feature_movies_episodeNumber_label),
                            entryString = movie.episodeId.toString(),
                        )
                        movie.releaseDate?.let { date ->
                            EntryRow(
                                labelString = stringResource(R.string.feature_movies_releaseDate_label),
                                entryString = date.format(dateFormatter),
                            )
                        }
                        EntryRow(
                            labelString = stringResource(R.string.feature_movies_director_label),
                            entryString = movie.director,
                        )
                        EntryListRow(
                            labelString = stringResource(R.string.feature_movies_producers_label),
                            entryList = movie.producers,
                        )
                    }
                }

            }
        }
    }
}

@Composable
private fun InteractiveStarWarsCrawl(text: String) {
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        scrollState.animateScrollTo(
            value = scrollState.maxValue,
            animationSpec = tween(
                durationMillis = 45000,
                easing = LinearEasing
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.openingCrawlSize)
            .padding(bottom = MaterialTheme.spacing.spacingEntryColumn)
            .clipToBounds()
            .graphicsLayer {
                // Rotation of text
                rotationX = 55f
                // Perspective of text
                cameraDistance = 12f * density
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.openingCrawlSize))
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = StarWarsColor,
                    textAlign = TextAlign.Justify,
                    lineHeight = 30.sp
                ),
            )
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