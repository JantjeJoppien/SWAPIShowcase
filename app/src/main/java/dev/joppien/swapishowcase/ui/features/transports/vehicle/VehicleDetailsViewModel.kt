package dev.joppien.swapishowcase.ui.features.transports.vehicle

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.joppien.swapishowcase.domain.usecases.transport.GetVehicleByIdUseCase
import dev.joppien.swapishowcase.ui.navigation.AppArgs
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class VehicleDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getVehicleById: GetVehicleByIdUseCase,
) : ViewModel() {

    private val vehicleId: Int = savedStateHandle.get<Int>(AppArgs.TRANSPORT_ID_ARG)
        ?: throw IllegalArgumentException("Vehicle ID not found in navigation arguments")

    val uiState: StateFlow<VehicleUiState> =
        getVehicleById(vehicleId)
            .map { vehicle ->
                if (vehicle != null) {
                    VehicleState(
                        name = vehicle.name,
                        model = vehicle.model,
                        vehicleClass = vehicle.vehicleClass,
                        manufacturer = vehicle.manufacturer,
                        costInCredits = vehicle.costInCredits?.toString() ?: "Unknown",
                        length = vehicle.length?.toString() ?: "Unknown",
                        maxAtmospheringSpeed = vehicle.maxAtmospheringSpeed,
                        cargoCapacity = vehicle.cargoCapacity?.toString() ?: "Unknown",
                        consumables = vehicle.consumables
                    )
                } else {
                    VehicleEmptyState()
                }
            }
            .onStart<VehicleUiState> {
                emit(VehicleLoadingState())
            }
            .catch<VehicleUiState> { exception ->
                emit(VehicleErrorState(exception))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = VehicleLoadingState()
            )

}