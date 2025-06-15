package dev.joppien.swapishowcase.ui.features.transports.vehicle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleDetailsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<VehicleUiState>(VehicleLoadingState())
    val uiState: StateFlow<VehicleUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    fun refreshData() {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.emit(VehicleLoadingState())
            try {
                _uiState.emit(
                    VehicleState(
                        name = "Sand Crawler",
                        model = "Digger Crawler",
                        vehicleClass = "wheeled",
                        manufacturer = "Corellia Mining Corporation",
                        costInCredits = "150000",
                        length = "36.8",
                        maxAtmospheringSpeed = "3000 km/h",
                        cargoCapacity = "50000",
                        consumables = "2 months",
                    )
                )
            } catch (e: Exception) {
                _uiState.emit(VehicleErrorState())
            }
        }
    }

}