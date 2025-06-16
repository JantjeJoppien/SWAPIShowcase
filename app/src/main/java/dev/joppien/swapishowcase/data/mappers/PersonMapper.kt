package dev.joppien.swapishowcase.data.mappers

import dev.joppien.swapishowcase.data.local.entity.PersonEntity
import dev.joppien.swapishowcase.data.remote.dto.PersonDTO
import dev.joppien.swapishowcase.domain.model.Person
import dev.joppien.swapishowcase.util.extractIdFromUrl
import dev.joppien.swapishowcase.util.safeToInt

fun PersonDTO.toEntity(): PersonEntity? {
    val id = extractIdFromUrl(this.url) ?: return null
    return PersonEntity(
        id = id,
        name = this.name,
        height = this.height.safeToInt(),
        mass = this.mass.safeToInt(),
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

fun PersonEntity.toDomain(): Person {
    return Person(
        id = this.id,
        name = this.name,
        height = this.height,
        mass = this.mass,
        hairColor = this.hairColor,
        skinColor = this.skinColor,
        eyeColor = this.eyeColor,
        birthYear = this.birthYear,
        gender = this.gender,
        homeworldId = this.homeworldId,
    )
}

fun List<PersonEntity>.toDomain(): List<Person> {
    return this.map { it.toDomain() }
}