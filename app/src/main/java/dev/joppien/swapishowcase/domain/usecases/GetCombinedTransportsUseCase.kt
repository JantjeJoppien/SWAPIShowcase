package dev.joppien.swapishowcase.domain.usecases

import dev.joppien.swapishowcase.data.repository.StarshipRepository
import dev.joppien.swapishowcase.data.repository.VehicleRepository
import dev.joppien.swapishowcase.domain.model.TransportItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetCombinedTransportsUseCase @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val starshipRepository: StarshipRepository
) {
    operator fun invoke(): Flow<List<TransportItem>> {
        return combine(
            vehicleRepository.getAllVehicles(),
            starshipRepository.getAllStarships()
        ) { vehicles, starships ->
            val combinedList = mutableListOf<TransportItem>()

            vehicles.forEach { vehicle ->
                combinedList.add(TransportItem.VehicleItem(vehicle))
            }
            starships.forEach { starship ->
                combinedList.add(TransportItem.StarshipItem(starship))
            }

            combinedList.shuffled()
        }
    }
}