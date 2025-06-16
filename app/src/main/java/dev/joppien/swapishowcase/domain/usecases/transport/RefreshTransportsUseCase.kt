package dev.joppien.swapishowcase.domain.usecases.transport

import dev.joppien.swapishowcase.data.repository.StarshipRepository
import dev.joppien.swapishowcase.data.repository.VehicleRepository
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