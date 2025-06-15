package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.remote.api.SwapiClient
import dev.joppien.swapishowcase.data.remote.dto.PlanetDTO

class PlanetRepository {

    //region Public API

    suspend fun getAllPlanets(page: Int? = null): List<PlanetDTO> {
        return fetchAll(page)
    }

    suspend fun getPlanetById(id: Int): PlanetDTO? {
        return fetchById(id)
    }

    //endregion

    //region Private API

    private suspend fun fetchAll(page: Int?): List<PlanetDTO> {
        return try {
            SwapiClient.swapiService.getAllPlanets(page).results
        } catch (e: Exception) {
            emptyList()
        }
    }

    private suspend fun fetchById(id: Int): PlanetDTO? {
        return try {
            SwapiClient.swapiService.getPlanetById(id)
        } catch (e: Exception) {
            null
        }
    }

    //endregion
}