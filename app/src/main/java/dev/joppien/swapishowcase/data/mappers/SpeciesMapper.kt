package dev.joppien.swapishowcase.data.mappers

import dev.joppien.swapishowcase.data.local.entity.SpeciesEntity
import dev.joppien.swapishowcase.data.remote.dto.SpeciesDTO
import dev.joppien.swapishowcase.util.extractIdFromUrl

fun SpeciesDTO.toEntity(): SpeciesEntity? {
    val id = extractIdFromUrl(this.url) ?: return null
    return SpeciesEntity(
        id = id,
        name = this.name,
        classification = this.classification,
        designation = this.designation,
        averageHeight = this.averageHeight,
        skinColors = this.skinColors,
        hairColors = this.hairColors,
        eyeColors = this.eyeColors,
        averageLifespan = this.averageLifespan,
        homeworldId = extractIdFromUrl(this.homeworld),
        language = this.language,
    )
}

fun List<SpeciesDTO>.toEntityList(): List<SpeciesEntity> {
    return this.mapNotNull { it.toEntity() }
}
