package dev.joppien.swapishowcase.data.remote.dto

import com.squareup.moshi.Json

data class PlanetDTO(
    @param:Json(name = "url")
    val url: String,

    @param:Json(name = "name")
    val name: String,

    @param:Json(name = "diameter")
    val diameter: String,

    @param:Json(name = "rotation_period")
    val rotationPeriod: String,

    @param:Json(name = "orbital_period")
    val orbitalPeriod: String,

    @param:Json(name = "gravity")
    val gravity: String,

    @param:Json(name = "population")
    val population: String,

    @param:Json(name = "climate")
    val climate: String,

    @param:Json(name = "terrain")
    val terrain: String,

    @param:Json(name = "surface_water")
    val surfaceWater: String,

    @param:Json(name = "residents")
    val residents: List<String>,

    @param:Json(name = "films")
    val films: List<String>,
)