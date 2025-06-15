package dev.joppien.swapishowcase.ui.features.transports.list

abstract class TransportListUiState

class TransportListLoadingState : TransportListUiState()

class TransportListErrorState : TransportListUiState()

class TransportListState(val movies: List<SimpleVehicleUi>) : TransportListUiState()

data class SimpleVehicleUi(
    val id: Int,
    val name: String,
    val model: String,
    val costInCredits: String,
)