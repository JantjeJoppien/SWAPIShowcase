package dev.joppien.swapishowcase.data.remote.dto

import com.squareup.moshi.Json

data class VehicleDTO(
    @param:Json(name = "url")
    val url: String,

    @param:Json(name = "name")
    val name: String,

    @param:Json(name = "model")
    val model: String,

    @param:Json(name = "vehicle_class")
    val vehicleClass: String,

    @param:Json(name = "manufacturer")
    val manufacturer: String,

    @param:Json(name = "length")
    val length: String,

    @param:Json(name = "cost_in_credits")
    val costInCredits: String,

    @param:Json(name = "crew")
    val crew: String,

    @param:Json(name = "passengers")
    val passengers: String,

    @param:Json(name = "max_atmosphering_speed")
    val maxAtmospheringSpeed: String,

    @param:Json(name = "cargo_capacity")
    val cargoCapacity: String,

    @param:Json(name = "consumables")
    val consumables: String,

    @param:Json(name = "films")
    val films: List<String>,

    @param:Json(name = "pilots")
    val pilots: List<String>,
)