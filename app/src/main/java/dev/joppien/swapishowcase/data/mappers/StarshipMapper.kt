package dev.joppien.swapishowcase.data.mappers

import dev.joppien.swapishowcase.data.local.entity.StarshipEntity
import dev.joppien.swapishowcase.data.remote.dto.StarshipDTO
import dev.joppien.swapishowcase.util.extractIdFromUrl

fun StarshipDTO.toEntity(): StarshipEntity? {
    val id = extractIdFromUrl(this.url) ?: return null
    return StarshipEntity(
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
        hyperdriveRating = this.hyperdriveRating,
        mglt = this.mglt,
        starshipClass = this.starshipClass,
    )
}

fun List<StarshipDTO>.toEntityList(): List<StarshipEntity> {
    return this.mapNotNull { it.toEntity() }
}
