package dev.joppien.swapishowcase.domain.model

sealed interface TransportItem {
    val id: Int
    val name: String
    val model: String
    val manufacturer: String
    val costInCredits: Long?
    val itemType: ItemType

    data class VehicleItem(val vehicle: Vehicle) : TransportItem {
        override val id: Int get() = vehicle.id
        override val name: String get() = vehicle.name
        override val model: String get() = vehicle.model
        override val manufacturer: String get() = vehicle.manufacturer
        override val costInCredits: Long? get() = vehicle.costInCredits
        override val itemType: ItemType get() = ItemType.VEHICLE
    }

    data class StarshipItem(val starship: Starship) : TransportItem {
        override val id: Int get() = starship.id
        override val name: String get() = starship.name
        override val model: String get() = starship.model
        override val manufacturer: String get() = starship.manufacturer
        override val costInCredits: Long? get() = starship.costInCredits
        override val itemType: ItemType get() = ItemType.STARSHIP
    }
}

enum class ItemType {
    VEHICLE,
    STARSHIP
}