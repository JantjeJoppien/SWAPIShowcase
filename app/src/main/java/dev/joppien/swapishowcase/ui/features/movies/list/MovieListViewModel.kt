package dev.joppien.swapishowcase.ui.features.movies.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.joppien.swapishowcase.domain.usecases.movies.GetMoviesUseCase
import dev.joppien.swapishowcase.domain.usecases.movies.RefreshMoviesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    getMovies: GetMoviesUseCase,
    private val refreshMovies: RefreshMoviesUseCase,
) : ViewModel() {

    val uiState: StateFlow<MovieListUiState> =
        getMovies()
            .map { movieItems ->
                if (movieItems.isNotEmpty()) {
                    MovieListState(movieItems.map {
                        SimpleMovieUi(
                            id = it.id,
                            title = it.title,
                            episodeId = it.episodeId,
                            releaseDate = it.releaseDate,
                        )
                    })
                } else {
                    MovieListEmptyState()
                }
            }
            .onStart<MovieListUiState> {
                emit(MovieListLoadingState())
            }
            .catch<MovieListUiState> { exception ->
                emit(MovieListErrorState(exception))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = MovieListLoadingState()
            )

    fun refreshData() {
        viewModelScope.launch {
            refreshMovies()
        }
    }

}