package dev.joppien.swapishowcase.domain.model

data class Starship(
    val id: Int,
    val name: String,
    val model: String,
    val manufacturer: String,
    val costInCredits: Long,
    val length: Double,
    val maxAtmospheringSpeed: String,
    val crew: String,
    val passengers: String,
    val cargoCapacity: Long,
    val consumables: String,
    val hyperdriveRating: String,
    val mglt: String,
    val starshipClass: String,
)