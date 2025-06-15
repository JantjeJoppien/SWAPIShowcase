package dev.joppien.swapishowcase.ui.features.transports.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransportListViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<TransportListUiState>(TransportListLoadingState())
    val uiState: StateFlow<TransportListUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    fun refreshData() {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.emit(TransportListLoadingState())
            try {
                _uiState.emit(
                    TransportListState(
                        listOf(
                            SimpleTransportUi(
                                1,
                                TransportType.VEHICLE,
                                "Sand Crawler",
                                "Digger Crawler",
                                "150000"
                            ),
                            SimpleTransportUi(
                                2,
                                TransportType.STARSHIP,
                                "T-16 skyhopper",
                                "T-16 skyhopper",
                                "100000"
                            ),
                            SimpleTransportUi(
                                3,
                                TransportType.VEHICLE,
                                "X-34 landspeeder",
                                "X-34 landspeeder",
                                "100000"
                            ),
                        )
                    )
                )
            } catch (e: Exception) {
                _uiState.emit(TransportListErrorState())
            }
        }
    }

}