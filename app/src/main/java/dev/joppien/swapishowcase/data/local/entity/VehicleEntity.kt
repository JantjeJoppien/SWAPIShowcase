package dev.joppien.swapishowcase.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicles")
data class VehicleEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val model: String,
    val manufacturer: String,
    val costInCredits: Long?,
    val length: Double?,
    val maxAtmospheringSpeed: String,
    val crew: String,
    val passengers: String,
    val cargoCapacity: Long?,
    val consumables: String,
    val vehicleClass: String,
    val lastRefreshed: Long = System.currentTimeMillis()
)