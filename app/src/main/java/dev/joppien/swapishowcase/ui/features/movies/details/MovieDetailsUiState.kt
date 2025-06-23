package dev.joppien.swapishowcase.ui.features.movies.details

import java.time.LocalDate

abstract class MovieDetailsUiState

class MovieDetailsLoadingState : MovieDetailsUiState()

class MovieDetailsEmptyState : MovieDetailsUiState()

class MovieDetailsErrorState(exception: Throwable) : MovieDetailsUiState()

class MovieDetailsState(
    val id: Int,
    val title: String,
    val episodeId: Int,
    val releaseDate: LocalDate?,
    val director: String,
    val producers: List<String>,
    val openingCrawl: String,
) : MovieDetailsUiState()