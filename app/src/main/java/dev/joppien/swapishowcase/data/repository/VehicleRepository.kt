package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.remote.api.SwapiClient
import dev.joppien.swapishowcase.data.remote.dto.VehicleDTO

class VehicleRepository {

    //region Public API

    suspend fun getAllVehicles(page: Int? = null): List<VehicleDTO> {
        return fetchAll(page)
    }

    suspend fun getVehicleById(id: Int): VehicleDTO? {
        return fetchById(id)
    }

    //endregion

    //region Private API

    private suspend fun fetchAll(page: Int?): List<VehicleDTO> {
        return try {
            SwapiClient.swapiService.getAllVehicles(page).results
        } catch (e: Exception) {
            emptyList()
        }
    }

    private suspend fun fetchById(id: Int): VehicleDTO? {
        return try {
            SwapiClient.swapiService.getVehicleById(id)
        } catch (e: Exception) {
            null
        }
    }

    //endregion
}