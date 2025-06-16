package dev.joppien.swapishowcase.domain.usecases

import dev.joppien.swapishowcase.data.repository.StarshipRepository
import dev.joppien.swapishowcase.data.repository.VehicleRepository
import dev.joppien.swapishowcase.domain.model.TransportItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class RefreshTransportsUseCase @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val starshipRepository: StarshipRepository
) {
    suspend operator fun invoke() {
        vehicleRepository.refreshAllVehiclesFromNetwork()
        starshipRepository.refreshAllStarshipsFromNetwork()
    }
}