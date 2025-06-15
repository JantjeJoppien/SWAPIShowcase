package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.remote.api.SwapiClient
import dev.joppien.swapishowcase.data.remote.dto.SpeciesDTO

class SpeciesRepository {

    //region Public API

    suspend fun getAllSpecies(page: Int? = null): List<SpeciesDTO> {
        return fetchAll(page)
    }

    suspend fun getSpeciesById(id: Int): SpeciesDTO? {
        return fetchById(id)
    }

    //endregion

    //region Private API

    private suspend fun fetchAll(page: Int?): List<SpeciesDTO> {
        return try {
            SwapiClient.swapiService.getAllSpecies(page).results
        } catch (e: Exception) {
            emptyList()
        }
    }

    private suspend fun fetchById(id: Int): SpeciesDTO? {
        return try {
            SwapiClient.swapiService.getSpeciesById(id)
        } catch (e: Exception) {
            null
        }
    }

    //endregion
}