package dev.joppien.swapishowcase.data.mappers

import dev.joppien.swapishowcase.data.local.entity.PlanetEntity
import dev.joppien.swapishowcase.data.remote.dto.PlanetDTO
import dev.joppien.swapishowcase.util.extractIdFromUrl

fun PlanetDTO.toEntity(): PlanetEntity? {
    val id = extractIdFromUrl(this.url) ?: return null
    return PlanetEntity(
        id = id,
        name = this.name,
        rotationPeriod = this.rotationPeriod,
        orbitalPeriod = this.orbitalPeriod,
        diameter = this.diameter,
        climate = this.climate,
        gravity = this.gravity,
        terrain = this.terrain,
        surfaceWater = this.surfaceWater,
        population = this.population,
    )
}

fun List<PlanetDTO>.toEntityList(): List<PlanetEntity> {
    return this.mapNotNull { it.toEntity() }
}
