package dev.joppien.swapishowcase.data.remote.dto

import com.squareup.moshi.Json

data class MovieDTO(
    @Json(name = "url")
    val url: String,

    @Json(name = "title")
    val title: String,

    @Json(name = "episode_id")
    val episodeId: Int,

    @Json(name = "opening_crawl")
    val openingCrawl: String,

    @Json(name = "director")
    val director: String,

    @Json(name = "producer")
    val producer: String,

    @Json(name = "release_date")
    val releaseDate: String,

    @Json(name = "characters")
    val characters: List<String>,

    @Json(name = "planets")
    val planets: List<String>,

    @Json(name = "starships")
    val starships: List<String>,

    @Json(name = "vehicles")
    val vehicles: List<String>,

    @Json(name = "species")
    val species: List<String>,
)
