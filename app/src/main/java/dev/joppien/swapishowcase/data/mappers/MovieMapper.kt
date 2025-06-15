package dev.joppien.swapishowcase.data.mappers

import dev.joppien.swapishowcase.data.local.entity.MovieEntity
import dev.joppien.swapishowcase.data.remote.dto.MovieDTO
import dev.joppien.swapishowcase.util.extractIdFromUrl

fun MovieDTO.toEntity(): MovieEntity? {
    val id = extractIdFromUrl(this.url) ?: return null
    val releaseDate = try {
        java.time.LocalDate.parse(this.releaseDate)
    } catch (e: Exception) {
        //ToDo: Handle parsing error
        null
    }

    return MovieEntity(
        id = id,
        title = this.title,
        episodeId = this.episodeId,
        openingCrawl = this.openingCrawl,
        director = this.director,
        producer = this.producer,
        releaseDate = releaseDate,
        charactersUrls = this.characters.joinToString(","),
    )
}

fun List<MovieDTO>.toEntityList(): List<MovieEntity> {
    return this.mapNotNull { it.toEntity() }
}