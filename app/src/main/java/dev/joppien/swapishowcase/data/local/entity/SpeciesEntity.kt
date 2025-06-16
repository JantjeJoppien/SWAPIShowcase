package dev.joppien.swapishowcase.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "species_table")
data class SpeciesEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val classification: String,
    val designation: String,
    val averageHeight: Double?,
    val skinColors: String,
    val hairColors: String,
    val eyeColors: String,
    val averageLifespan: Double?,
    val homeworldId: Int?,
    val language: String,
    val lastRefreshed: Long = System.currentTimeMillis()
)