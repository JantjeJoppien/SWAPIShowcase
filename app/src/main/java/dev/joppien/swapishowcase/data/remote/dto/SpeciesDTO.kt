package dev.joppien.swapishowcase.data.remote.dto

import com.squareup.moshi.Json

data class SpeciesDTO(
    @Json(name = "url")
    val url: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "classification")
    val classification: String,

    @Json(name = "designation")
    val designation: String,

    @Json(name = "average_height")
    val averageHeight: String,

    @Json(name = "average_lifespan")
    val averageLifespan: String,

    @Json(name = "eye_colors")
    val eyeColors: String,

    @Json(name = "hair_colors")
    val hairColors: String,

    @Json(name = "skin_colors")
    val skinColors: String,

    @Json(name = "language")
    val language: String,

    @Json(name = "homeworld")
    val homeworld: String?,

    @Json(name = "people")
    val people: List<String>,

    @Json(name = "films")
    val films: List<String>,
)