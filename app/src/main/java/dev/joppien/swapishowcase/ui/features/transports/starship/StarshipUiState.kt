package dev.joppien.swapishowcase.ui.features.transports.starship

abstract class StarshipUiState

class StarshipLoadingState : StarshipUiState()

class StarshipErrorState : StarshipUiState()

class StarshipState(
    val name: String,
    val model: String,
    val starshipClass: String,
    val manufacturer: String,
    val costInCredits: String,
    val length: String,
    val maxAtmospheringSpeed: String,
    val hyperdriveRating: String,
    val mglt: String,
    val cargoCapacity: String,
    val consumables: String,
) : StarshipUiState()