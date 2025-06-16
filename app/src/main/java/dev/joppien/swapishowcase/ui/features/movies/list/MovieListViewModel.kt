package dev.joppien.swapishowcase.ui.features.movies.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.joppien.swapishowcase.domain.model.TransportItem
import dev.joppien.swapishowcase.domain.usecases.movies.GetMoviesUseCase
import dev.joppien.swapishowcase.domain.usecases.movies.RefreshMoviesUseCase
import dev.joppien.swapishowcase.ui.features.transports.list.SimpleTransportUi
import dev.joppien.swapishowcase.ui.features.transports.list.TransportListEmptyState
import dev.joppien.swapishowcase.ui.features.transports.list.TransportListErrorState
import dev.joppien.swapishowcase.ui.features.transports.list.TransportListLoadingState
import dev.joppien.swapishowcase.ui.features.transports.list.TransportListState
import dev.joppien.swapishowcase.ui.features.transports.list.TransportListUiState
import dev.joppien.swapishowcase.ui.features.transports.list.TransportType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
                            releaseDate = it.releaseDate.toString(),
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