package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.remote.api.SwapiClient
import dev.joppien.swapishowcase.data.remote.dto.StarshipDTO

class StarshipRepository {

    //region Public API

    suspend fun getAllStarships(page: Int? = null): List<StarshipDTO> {
        return fetchAll(page)
    }

    suspend fun getStarshipById(id: Int): StarshipDTO? {
        return fetchById(id)
    }

    //endregion

    //region Private API

    private suspend fun fetchAll(page: Int?): List<StarshipDTO> {
        return try {
            SwapiClient.swapiService.getAllStarships(page).results
        } catch (e: Exception) {
            emptyList()
        }
    }

    private suspend fun fetchById(id: Int): StarshipDTO? {
        return try {
            SwapiClient.swapiService.getStarshipById(id)
        } catch (e: Exception) {
            null
        }
    }

    //endregion
}