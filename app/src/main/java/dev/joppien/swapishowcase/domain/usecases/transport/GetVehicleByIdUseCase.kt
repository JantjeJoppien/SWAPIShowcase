package dev.joppien.swapishowcase.domain.usecases.transport

import dev.joppien.swapishowcase.data.repository.VehicleRepository
import dev.joppien.swapishowcase.domain.model.Vehicle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVehicleByIdUseCase @Inject constructor(
    private val vehicleRepository: VehicleRepository,
) {
    operator fun invoke(id: Int): Flow<Vehicle?> {
        return vehicleRepository.getVehicleById(id = id)
    }
}