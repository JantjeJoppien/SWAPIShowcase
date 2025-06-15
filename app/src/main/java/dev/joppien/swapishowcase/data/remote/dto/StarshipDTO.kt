package dev.joppien.swapishowcase.data.remote.dto

import com.squareup.moshi.Json

data class StarshipDTO(
    @Json(name = "url")
    val url: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "model")
    val model: String,

    @Json(name = "starship_class")
    val starshipClass: String,

    @Json(name = "manufacturer")
    val manufacturer: String,

    @Json(name = "cost_in_credits")
    val costInCredits: String,

    @Json(name = "length")
    val length: String,

    @Json(name = "crew")
    val crew: String,

    @Json(name = "passengers")
    val passengers: String,

    @Json(name = "max_atmosphering_speed")
    val maxAtmospheringSpeed: String,

    @Json(name = "hyperdrive_rating")
    val hyperdriveRating: String,

    @Json(name = "MGLT")
    val mglt: String,

    @Json(name = "cargo_capacity")
    val cargoCapacity: String,

    @Json(name = "consumables")
    val consumables: String,

    @Json(name = "films")
    val films: List<String>,

    @Json(name = "pilots")
    val pilots: List<String>,
)