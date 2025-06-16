package dev.joppien.swapishowcase.domain.model

data class Person(
    val id: Int,
    val name: String,
    val height: Int?,
    val mass: Int?,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
    val homeworldId: Int?,
)