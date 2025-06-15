package dev.joppien.swapishowcase.ui.features.movies.details

abstract class MovieDetailsUiState

class MovieDetailsLoadingState : MovieDetailsUiState()

class MovieDetailsErrorState : MovieDetailsUiState()

class MovieDetailsState(
    val id: Int,
    val title: String,
    val episodeId: Int,
    val releaseDate: String,
    val director: String,
    val producer: List<String>,
    val openingCrawl: String,
) : MovieDetailsUiState()