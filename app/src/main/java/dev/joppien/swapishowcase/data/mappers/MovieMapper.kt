package dev.joppien.swapishowcase.data.mappers

import dev.joppien.swapishowcase.data.local.entity.MovieEntity
import dev.joppien.swapishowcase.data.remote.dto.MovieDTO
import dev.joppien.swapishowcase.domain.model.Movie
import dev.joppien.swapishowcase.util.extractIdFromUrl
import dev.joppien.swapishowcase.util.toTrimmedStringList
import timber.log.Timber

fun MovieDTO.toEntity(): MovieEntity? {
    val id = extractIdFromUrl(this.url) ?: return null
    val releaseDate = try {
        java.time.LocalDate.parse(this.releaseDate)
    } catch (e: Exception) {
        Timber.e(e, "Error extracting release date from movie")
        null
    }

    return MovieEntity(
        id = id,
        title = this.title,
        episodeId = this.episodeId,
        openingCrawl = this.openingCrawl,
        director = this.director,
        producers = this.producers,
        releaseDate = releaseDate,
    )
}

fun List<MovieDTO>.toEntityList(): List<MovieEntity> {
    return this.mapNotNull { it.toEntity() }
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        episodeId = this.episodeId,
        openingCrawl = this.openingCrawl,
        director = this.director,
        producers = this.producers.toTrimmedStringList(),
        releaseDate = this.releaseDate,
    )
}

fun List<MovieEntity>.toDomain(): List<Movie> {
    return this.map { it.toDomain() }
}