package dev.joppien.swapishowcase.data.remote.dto

import com.squareup.moshi.Json

data class PersonDTO(
    @param:Json(name = "url")
    val url: String,

    @param:Json(name = "name")
    val name: String,

    @param:Json(name = "birth_year")
    val birthYear: String,

    @param:Json(name = "eye_color")
    val eyeColor: String,

    @param:Json(name = "gender")
    val gender: String,

    @param:Json(name = "hair_color")
    val hairColor: String,

    @param:Json(name = "height")
    val height: String,

    @param:Json(name = "mass")
    val mass: String,

    @param:Json(name = "skin_color")
    val skinColor: String,

    @param:Json(name = "homeworld")
    val homeworld: String,

    @param:Json(name = "films")
    val films: List<String>,

    @param:Json(name = "species")
    val species: List<String>,

    @param:Json(name = "starships")
    val starships: List<String>,

    @param:Json(name = "vehicles")
    val vehicles: List<String>,
)