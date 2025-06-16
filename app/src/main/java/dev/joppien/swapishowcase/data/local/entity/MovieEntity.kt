package dev.joppien.swapishowcase.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dev.joppien.swapishowcase.data.local.Converters
import java.time.LocalDate

@Entity(tableName = "movies")
@TypeConverters(Converters::class)
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val episodeId: Int,
    val openingCrawl: String,
    val director: String,
    val producers: String,
    val releaseDate: LocalDate?,
    val lastRefreshed: Long = System.currentTimeMillis()
)
