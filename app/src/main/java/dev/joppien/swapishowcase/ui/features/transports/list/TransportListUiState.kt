package dev.joppien.swapishowcase.ui.features.transports.list

abstract class TransportListUiState

class TransportListLoadingState : TransportListUiState()

class TransportListEmptyState : TransportListUiState()

class TransportListErrorState(exception: Throwable) : TransportListUiState()

class TransportListState(val vehicles: List<SimpleTransportUi>) : TransportListUiState()

data class SimpleTransportUi(
    val id: Int,
    val type: TransportType,
    val name: String,
    val model: String,
    val costInCredits: String,
)

enum class TransportType {
    VEHICLE, STARSHIP
}