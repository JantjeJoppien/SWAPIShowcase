package dev.joppien.swapishowcase.domain.model

data class Species(
    val id: Int,
    val name: String,
    val classification: String,
    val designation: String,
    val averageHeight: Double,
    val skinColors: List<String>,
    val hairColors: List<String>,
    val eyeColors: List<String>,
    val averageLifespan: Double,
    val homeworldId: Int?,
    val language: String,
)