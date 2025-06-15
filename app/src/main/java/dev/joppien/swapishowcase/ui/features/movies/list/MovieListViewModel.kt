package dev.joppien.swapishowcase.ui.features.movies.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<MovieListUiState>(MovieListLoadingState())
    val uiState: StateFlow<MovieListUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    fun refreshData() {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.value = MovieListLoadingState()
            try {
                _uiState.value = MovieListState(
                    movies = listOf(
                        SimpleMovieUi(4, "A New Hope", 4, "1977-05-25"),
                        SimpleMovieUi(5, "The Empire Strikes Back", 5, "1980-05-17"),
                        SimpleMovieUi(6, "Return of the Jedi", 6, "1983-05-25"),
                    )
                )
            } catch (e: Exception) {
                _uiState.value = MovieListErrorState()
            }
        }
    }

}