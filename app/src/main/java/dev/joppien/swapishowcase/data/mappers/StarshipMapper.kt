package dev.joppien.swapishowcase.data.mappers

import dev.joppien.swapishowcase.data.local.entity.StarshipEntity
import dev.joppien.swapishowcase.data.remote.dto.StarshipDTO
import dev.joppien.swapishowcase.domain.model.Starship
import dev.joppien.swapishowcase.util.extractIdFromUrl
import dev.joppien.swapishowcase.util.safeToDouble
import dev.joppien.swapishowcase.util.safeToLong

fun StarshipDTO.toEntity(): StarshipEntity? {
    val id = extractIdFromUrl(this.url) ?: return null
    return StarshipEntity(
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
        hyperdriveRating = this.hyperdriveRating,
        mglt = this.mglt,
        starshipClass = this.starshipClass,
    )
}

fun List<StarshipDTO>.toEntityList(): List<StarshipEntity> {
    return this.mapNotNull { it.toEntity() }
}

fun StarshipEntity.toDomain(): Starship {
    return Starship(
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
        hyperdriveRating = this.hyperdriveRating,
        mglt = this.mglt,
        starshipClass = this.starshipClass,
    )
}

fun List<StarshipEntity>.toDomain(): List<Starship> {
    return this.map { it.toDomain() }
}