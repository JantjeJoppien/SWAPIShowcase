package dev.joppien.swapishowcase.domain.model

import java.time.LocalDate

data class Movie(
    val id: Int,
    val title: String,
    val episodeId: Int,
    val openingCrawl: String,
    val director: String,
    val producer: String,
    val releaseDate: LocalDate?,
)