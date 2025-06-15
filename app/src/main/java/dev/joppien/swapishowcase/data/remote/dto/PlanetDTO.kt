package dev.joppien.swapishowcase.data.remote.dto

import com.squareup.moshi.Json

data class PlanetDTO(
    @Json(name = "url")
    val url: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "diameter")
    val diameter: String,

    @Json(name = "rotation_period")
    val rotationPeriod: String,

    @Json(name = "orbital_period")
    val orbitalPeriod: String,

    @Json(name = "gravity")
    val gravity: String,

    @Json(name = "population")
    val population: String,

    @Json(name = "climate")
    val climate: String,

    @Json(name = "terrain")
    val terrain: String,

    @Json(name = "surface_water")
    val surfaceWater: String,

    @Json(name = "residents")
    val residents: List<String>,

    @Json(name = "films")
    val films: List<String>,
)