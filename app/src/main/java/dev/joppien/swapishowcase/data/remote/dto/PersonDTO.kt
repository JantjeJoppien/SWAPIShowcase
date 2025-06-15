package dev.joppien.swapishowcase.data.remote.dto

import com.squareup.moshi.Json

data class PersonDTO(
    @Json(name = "url")
    val url: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "birth_year")
    val birthYear: String,

    @Json(name = "eye_color")
    val eyeColor: String,

    @Json(name = "gender")
    val gender: String,

    @Json(name = "hair_color")
    val hairColor: String,

    @Json(name = "height")
    val height: String,

    @Json(name = "mass")
    val mass: String,

    @Json(name = "skin_color")
    val skinColor: String,

    @Json(name = "homeworld")
    val homeworld: String,

    @Json(name = "films")
    val films: List<String>,

    @Json(name = "species")
    val species: List<String>,

    @Json(name = "starships")
    val starships: List<String>,

    @Json(name = "vehicles")
    val vehicles: List<String>,
)