package dev.joppien.swapishowcase.ui.features.movies.list

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.joppien.swapishowcase.R
import dev.joppien.swapishowcase.ui.navigation.AppScreens
import dev.joppien.swapishowcase.ui.theme.SWAPIAppTheme
import dev.joppien.swapishowcase.ui.theme.spacing
import dev.joppien.swapishowcase.ui.util.rememberPreviewNavController
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(R.string.feature_movies_title),
                    style = MaterialTheme.typography.headlineMedium,
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

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = MaterialTheme.spacing.gridMinSize),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacingCardGrid),
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacingCardGrid),
                    ) {
                        items(
                            count = movies.size,
                            key = { index -> movies[index].id }
                        ) { index ->
                            val movie = movies[index]
                            val dateFormatter = remember {
                                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                            }

                            Card(
                                modifier = Modifier.clickable {
                                    navController.navigate("${AppScreens.MOVIE_DETAILS_SCREEN}/${movie.id}")
                                }) {
                                Column(
                                    modifier = Modifier.padding(
                                        start = MaterialTheme.spacing.paddingCardSides,
                                        end = MaterialTheme.spacing.paddingCardSides,
                                        top = MaterialTheme.spacing.paddingCardTopBottom,
                                        bottom = MaterialTheme.spacing.paddingCardTopBottom,
                                    ),
                                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacingCard),
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = movie.episodeId.toString(),
                                        style = MaterialTheme.typography.bodyMedium,
                                        textAlign = TextAlign.Center,
                                    )
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = movie.title,
                                        style = MaterialTheme.typography.titleMedium,
                                        textAlign = TextAlign.Center,
                                    )
                                    movie.releaseDate?.let { date ->
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = date.format(dateFormatter),
                                            style = MaterialTheme.typography.bodySmall,
                                            textAlign = TextAlign.Center,
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
}

@Preview(showBackground = true)
@Composable
fun MovieListScreenPreview() {
    val previewNavController = rememberPreviewNavController()
    SWAPIAppTheme {
        MovieListScreen(navController = previewNavController)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovieListScreenDarkPreview() {
    val previewNavController = rememberPreviewNavController()
    SWAPIAppTheme {
        MovieListScreen(navController = previewNavController)
    }
}