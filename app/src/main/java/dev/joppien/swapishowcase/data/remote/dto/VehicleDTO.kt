package dev.joppien.swapishowcase.data.remote.dto

import com.squareup.moshi.Json

data class VehicleDTO(
    @Json(name = "url")
    val url: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "model")
    val model: String,

    @Json(name = "vehicle_class")
    val vehicleClass: String,

    @Json(name = "manufacturer")
    val manufacturer: String,

    @Json(name = "length")
    val length: String,

    @Json(name = "cost_in_credits")
    val costInCredits: String,

    @Json(name = "crew")
    val crew: String,

    @Json(name = "passengers")
    val passengers: String,

    @Json(name = "max_atmosphering_speed")
    val maxAtmospheringSpeed: String,

    @Json(name = "cargo_capacity")
    val cargoCapacity: String,

    @Json(name = "consumables")
    val consumables: String,

    @Json(name = "films")
    val films: List<String>,

    @Json(name = "pilots")
    val pilots: List<String>,
)