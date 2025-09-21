package dev.joppien.swapishowcase.data.remote.dto

import com.squareup.moshi.Json

data class SpeciesDTO(
    @param:Json(name = "url")
    val url: String,

    @param:Json(name = "name")
    val name: String,

    @param:Json(name = "classification")
    val classification: String,

    @param:Json(name = "designation")
    val designation: String,

    @param:Json(name = "average_height")
    val averageHeight: String,

    @param:Json(name = "average_lifespan")
    val averageLifespan: String,

    @param:Json(name = "eye_colors")
    val eyeColors: String,

    @param:Json(name = "hair_colors")
    val hairColors: String,

    @param:Json(name = "skin_colors")
    val skinColors: String,

    @param:Json(name = "language")
    val language: String,

    @param:Json(name = "homeworld")
    val homeworld: String?,

    @param:Json(name = "people")
    val people: List<String>,

    @param:Json(name = "films")
    val films: List<String>,
)