package dev.joppien.swapishowcase.data.mappers

import dev.joppien.swapishowcase.data.local.entity.VehicleEntity
import dev.joppien.swapishowcase.data.remote.dto.VehicleDTO
import dev.joppien.swapishowcase.domain.model.Vehicle
import dev.joppien.swapishowcase.util.extractIdFromUrl
import dev.joppien.swapishowcase.util.safeToDouble
import dev.joppien.swapishowcase.util.safeToLong

fun VehicleDTO.toEntity(): VehicleEntity? {
    val id = extractIdFromUrl(this.url) ?: return null
    return VehicleEntity(
        id = id,
        name = this.name,
        model = this.model,
        manufacturer = this.manufacturer,
        costInCredits = this.costInCredits.safeToLong(),
        length = this.length.safeToDouble(),
        maxAtmospheringSpeed = this.maxAtmospheringSpeed,
        crew = this.crew,
        passengers = this.passengers,
        cargoCapacity = this.cargoCapacity.safeToLong(),
        consumables = this.consumables,
        vehicleClass = this.vehicleClass,
    )
}

fun List<VehicleDTO>.toEntityList(): List<VehicleEntity> {
    return this.mapNotNull { it.toEntity() }
}

fun VehicleEntity.toDomain(): Vehicle {
    return Vehicle(
        id = this.id,
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

fun List<VehicleEntity>.toDomain(): List<Vehicle> {
    return this.map { it.toDomain() }
}