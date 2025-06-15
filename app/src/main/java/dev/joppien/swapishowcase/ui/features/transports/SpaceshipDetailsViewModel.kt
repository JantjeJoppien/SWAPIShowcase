package dev.joppien.swapishowcase.ui.features.transports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SpaceshipDetailsUiState(
    val isLoading: Boolean = false,
    val data: String = "Hello from Spaceship!",
    val error: String? = null
)

@HiltViewModel
class SpaceshipDetailsViewModel @Inject constructor(
    // Inject your repositories or use cases here
    // Example: private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SpaceshipDetailsUiState())
    val uiState: StateFlow<SpaceshipDetailsUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    fun refreshData() {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Failed to load data: ${e.message}"
                )
            }
        }
    }

}