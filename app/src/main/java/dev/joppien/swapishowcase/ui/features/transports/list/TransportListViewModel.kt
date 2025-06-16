package dev.joppien.swapishowcase.ui.features.transports.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.joppien.swapishowcase.domain.model.TransportItem
import dev.joppien.swapishowcase.domain.usecases.transport.GetCombinedTransportsUseCase
import dev.joppien.swapishowcase.domain.usecases.transport.RefreshTransportsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransportListViewModel @Inject constructor(
    getCombinedTransports: GetCombinedTransportsUseCase,
    val refreshTransports: RefreshTransportsUseCase,
) : ViewModel() {

    val uiState: StateFlow<TransportListUiState> =
        getCombinedTransports()
            .map { combinedTransportItems ->
                if (combinedTransportItems.isNotEmpty()) {
                    TransportListState(combinedTransportItems.map {
                        SimpleTransportUi(
                            id = it.id,
                            type = when (it) {
                                is TransportItem.VehicleItem -> TransportType.VEHICLE
                                is TransportItem.StarshipItem -> TransportType.STARSHIP
                            },
                            name = it.name,
                            model = it.model,
                            costInCredits = it.costInCredits?.toString() ?: "Unknown"
                        )
                    })
                } else {
                    TransportListEmptyState()
                }
            }
            .onStart<TransportListUiState> {
                emit(TransportListLoadingState())
            }
            .catch<TransportListUiState> { exception ->
                emit(TransportListErrorState(exception))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = TransportListLoadingState()
            )


    fun refreshData() {
        viewModelScope.launch {
            refreshTransports()
        }
    }

}