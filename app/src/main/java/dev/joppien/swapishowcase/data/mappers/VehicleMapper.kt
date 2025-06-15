package dev.joppien.swapishowcase.data.mappers

import dev.joppien.swapishowcase.data.local.entity.VehicleEntity
import dev.joppien.swapishowcase.data.remote.dto.VehicleDTO
import dev.joppien.swapishowcase.util.extractIdFromUrl

fun VehicleDTO.toEntity(): VehicleEntity? {
    val id = extractIdFromUrl(this.url) ?: return null
    return VehicleEntity(
        id = id,
        name = this.name,
        model = this.model,
        manufacturer = this.manufacturer,
        costInCredits = this.costInCredits,
        length = this.length,
        maxAtmospheringSpeed = this.maxAtmospheringSpeed,
        crew = this.crew,
        passengers = this.passengers,
        cargoCapacity = this.cargoCapacity,
        consumables = this.consumables,
        vehicleClass = this.vehicleClass,
    )
}

fun List<VehicleDTO>.toEntityList(): List<VehicleEntity> {
    return this.mapNotNull { it.toEntity() }
}
