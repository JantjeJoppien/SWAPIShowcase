package dev.joppien.swapishowcase.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Changed table name to avoid conflict with SQL keyword
@Entity(tableName = "species_table")
data class SpeciesEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val classification: String,
    val designation: String,
    val averageHeight: String,
    val skinColors: String,
    val hairColors: String,
    val eyeColors: String,
    val averageLifespan: String,
    val homeworldId: Int?,
    val language: String,
    val lastRefreshed: Long = System.currentTimeMillis()
)