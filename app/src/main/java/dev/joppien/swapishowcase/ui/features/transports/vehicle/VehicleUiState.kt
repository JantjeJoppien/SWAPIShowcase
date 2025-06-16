package dev.joppien.swapishowcase.ui.features.transports.vehicle

abstract class VehicleUiState

class VehicleLoadingState : VehicleUiState()

class VehicleEmptyState : VehicleUiState()

class VehicleErrorState(exception: Throwable) : VehicleUiState()

class VehicleState(
    val name: String,
    val model: String,
    val vehicleClass: String,
    val manufacturer: String,
    val costInCredits: String,
    val length: String,
    val maxAtmospheringSpeed: String,
    val cargoCapacity: String,
    val consumables: String,
) : VehicleUiState()