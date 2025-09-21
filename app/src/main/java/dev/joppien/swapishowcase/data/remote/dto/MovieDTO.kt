package dev.joppien.swapishowcase.data.remote.dto

import com.squareup.moshi.Json

data class MovieDTO(
    @param:Json(name = "url")
    val url: String,

    @param:Json(name = "title")
    val title: String,

    @param:Json(name = "episode_id")
    val episodeId: Int,

    @param:Json(name = "opening_crawl")
    val openingCrawl: String,

    @param:Json(name = "director")
    val director: String,

    @param:Json(name = "producer")
    val producers: String,

    @param:Json(name = "release_date")
    val releaseDate: String,

    @param:Json(name = "characters")
    val characters: List<String>,

    @param:Json(name = "planets")
    val planets: List<String>,

    @param:Json(name = "starships")
    val starships: List<String>,

    @param:Json(name = "vehicles")
    val vehicles: List<String>,

    @param:Json(name = "species")
    val species: List<String>,
)
