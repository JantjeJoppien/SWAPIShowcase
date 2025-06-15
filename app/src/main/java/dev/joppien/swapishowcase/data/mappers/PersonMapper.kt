package dev.joppien.swapishowcase.data.mappers

import dev.joppien.swapishowcase.data.local.entity.PersonEntity
import dev.joppien.swapishowcase.data.remote.dto.PersonDTO
import dev.joppien.swapishowcase.util.extractIdFromUrl

fun PersonDTO.toEntity(): PersonEntity? {
    val id = extractIdFromUrl(this.url) ?: return null
    return PersonEntity(
        id = id,
        name = this.name,
        height = this.height,
        mass = this.mass,
        hairColor = this.hairColor,
        skinColor = this.skinColor,
        eyeColor = this.eyeColor,
        birthYear = this.birthYear,
        gender = this.gender,
        homeworldId = extractIdFromUrl(this.homeworld),
    )
}

fun List<PersonDTO>.toEntityList(): List<PersonEntity> {
    return this.mapNotNull { it.toEntity() }
}
