package dev.joppien.swapishowcase.ui.features.movies.list

abstract class MovieListUiState

class MovieListLoadingState : MovieListUiState()

class MovieListErrorState : MovieListUiState()

class MovieListState(val movies: List<SimpleMovieUi>) : MovieListUiState()

data class SimpleMovieUi(
    val id: Int,
    val title: String,
    val episodeId: Int,
    val releaseDate: String,
)