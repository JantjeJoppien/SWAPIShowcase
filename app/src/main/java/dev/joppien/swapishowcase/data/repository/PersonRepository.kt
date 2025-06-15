package dev.joppien.swapishowcase.data.repository

import dev.joppien.swapishowcase.data.remote.api.SwapiClient
import dev.joppien.swapishowcase.data.remote.dto.PersonDTO

class PersonRepository {

    //region Public API

    suspend fun getAllPeople(page: Int? = null): List<PersonDTO> {
        return fetchAll(page)
    }

    suspend fun getPersonById(id: Int): PersonDTO? {
        return fetchById(id)
    }

    //endregion

    //region Private API

    private suspend fun fetchAll(page: Int?): List<PersonDTO> {
        return try {
            SwapiClient.swapiService.getAllPeople(page).results
        } catch (e: Exception) {
            emptyList()
        }
    }

    private suspend fun fetchById(id: Int): PersonDTO? {
        return try {
            SwapiClient.swapiService.getPersonById(id)
        } catch (e: Exception) {
            null
        }
    }

    //endregion
}