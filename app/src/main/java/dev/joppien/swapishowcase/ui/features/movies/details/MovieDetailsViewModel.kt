package dev.joppien.swapishowcase.ui.features.movies.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<MovieDetailsUiState>(MovieDetailsLoadingState())
    val uiState: StateFlow<MovieDetailsUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    fun refreshData() {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.emit(MovieDetailsLoadingState())
            try {
                _uiState.emit(
                    MovieDetailsState(
                        6,
                        "Return of the Jedi",
                        6,
                        "1983-05-25",
                        "Richard Marquand",
                        listOf("Howard G. Kazanjian", "George Lucas", "Rick McCallum"),
                        "Luke Skywalker once again falls into the dark side of the Force."
                    ),
                )
            } catch (e: Exception) {
                _uiState.emit(MovieDetailsErrorState())
            }
        }
    }

}