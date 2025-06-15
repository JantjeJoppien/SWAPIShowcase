package dev.joppien.swapishowcase.ui.features.transports.starship

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarshipDetailsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<StarshipUiState>(StarshipLoadingState())
    val uiState: StateFlow<StarshipUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    fun refreshData() {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.emit(StarshipLoadingState())
            try {
                _uiState.emit(
                    StarshipState(
                        name = "Death Star",
                        model = "DS-1 Orbital Battle Station",
                        starshipClass = "Deep Space Mobile Battlestation",
                        manufacturer = "Imperial Department of Military Research, Sienar Fleet Systems",
                        costInCredits = "1000000000000",
                        length = "120000",
                        maxAtmospheringSpeed = "n/a",
                        hyperdriveRating = "4.0",
                        mglt = "10",
                        cargoCapacity = "1000000000000",
                        consumables = "3 years",
                    )
                )
            } catch (e: Exception) {
                _uiState.emit(StarshipErrorState())
            }
        }
    }

}