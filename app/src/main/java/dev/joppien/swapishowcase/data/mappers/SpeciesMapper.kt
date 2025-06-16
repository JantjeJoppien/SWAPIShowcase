package dev.joppien.swapishowcase.data.mappers

import dev.joppien.swapishowcase.data.local.entity.SpeciesEntity
import dev.joppien.swapishowcase.data.remote.dto.SpeciesDTO
import dev.joppien.swapishowcase.domain.model.Species
import dev.joppien.swapishowcase.util.extractIdFromUrl
import dev.joppien.swapishowcase.util.safeToDouble
import dev.joppien.swapishowcase.util.toTrimmedStringList

fun SpeciesDTO.toEntity(): SpeciesEntity? {
    val id = extractIdFromUrl(this.url) ?: return null
    return SpeciesEntity(
        id = id,
        name = this.name,
        classification = this.classification,
        designation = this.designation,
        averageHeight = this.averageHeight.safeToDouble(),
        skinColors = this.skinColors,
        hairColors = this.hairColors,
        eyeColors = this.eyeColors,
        averageLifespan = this.averageLifespan.safeToDouble(),
        homeworldId = extractIdFromUrl(this.homeworld),
        language = this.language,
    )
}

fun List<SpeciesDTO>.toEntityList(): List<SpeciesEntity> {
    return this.mapNotNull { it.toEntity() }
}

fun SpeciesEntity.toDomain(): Species {
    return Species(
        id = this.id,
        name = this.name,
        classification = this.classification,
        designation = this.designation,
        averageHeight = this.averageHeight,
        skinColors = this.skinColors.toTrimmedStringList(),
        hairColors = this.hairColors.toTrimmedStringList(),
        eyeColors = this.eyeColors.toTrimmedStringList(),
        averageLifespan = this.averageLifespan,
        homeworldId = this.homeworldId,
        language = this.language,
    )
}

fun List<SpeciesEntity>.toDomain(): List<Species> {
    return this.map { it.toDomain() }
}