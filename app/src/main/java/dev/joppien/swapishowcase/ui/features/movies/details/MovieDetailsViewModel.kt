package dev.joppien.swapishowcase.ui.features.movies.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.joppien.swapishowcase.domain.usecases.movies.GetMovieByIdUseCase
import dev.joppien.swapishowcase.ui.navigation.AppArgs
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getMovieById: GetMovieByIdUseCase,
) : ViewModel() {

    private val movieId: Int = savedStateHandle.get<Int>(AppArgs.MOVIE_ID_ARG)
        ?: throw IllegalArgumentException("Movie ID not found in navigation arguments")

    val uiState: StateFlow<MovieDetailsUiState> =
        getMovieById(movieId)
            .map { movie ->
                if (movie != null) {
                    MovieDetailsState(
                        id = movie.id,
                        title = movie.title,
                        episodeId = movie.episodeId,
                        releaseDate = movie.releaseDate.toString(),
                        director = movie.director,
                        producers = movie.producers,
                        openingCrawl = movie.openingCrawl
                    )
                } else {
                    MovieDetailsEmptyState()
                }
            }
            .onStart<MovieDetailsUiState> {
                emit(MovieDetailsLoadingState())
            }
            .catch<MovieDetailsUiState> { exception ->
                emit(MovieDetailsErrorState(exception))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = MovieDetailsLoadingState()
            )

}