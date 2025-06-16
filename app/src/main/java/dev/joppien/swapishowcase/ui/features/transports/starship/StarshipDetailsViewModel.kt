package dev.joppien.swapishowcase.ui.features.transports.starship

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.joppien.swapishowcase.domain.usecases.transport.GetStarshipByIdUseCase
import dev.joppien.swapishowcase.ui.navigation.AppArgs
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StarshipDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getStarshipById: GetStarshipByIdUseCase,
) : ViewModel() {

    private val starshipId: Int = savedStateHandle.get<Int>(AppArgs.TRANSPORT_ID_ARG)
        ?: throw IllegalArgumentException("Starship ID not found in navigation arguments")

    val uiState: StateFlow<StarshipUiState> =
        getStarshipById(starshipId)
            .map { starship ->
                if (starship != null) {
                    StarshipState(
                        name = starship.name,
                        model = starship.model,
                        starshipClass = starship.starshipClass,
                        manufacturer = starship.manufacturer,
                        costInCredits = starship.costInCredits?.toString() ?: "Unknown",
                        length = starship.length?.toString() ?: "Unknown",
                        maxAtmospheringSpeed = starship.maxAtmospheringSpeed,
                        hyperdriveRating = starship.hyperdriveRating,
                        mglt = starship.mglt,
                        cargoCapacity = starship.cargoCapacity?.toString() ?: "Unknown",
                        consumables = starship.consumables
                    )
                } else {
                    StarshipEmptyState()
                }
            }
            .onStart<StarshipUiState> {
                emit(StarshipLoadingState())
            }
            .catch<StarshipUiState> { exception ->
                emit(StarshipErrorState(exception))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = StarshipLoadingState()
            )

}