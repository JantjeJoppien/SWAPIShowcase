package dev.joppien.swapishowcase.domain.usecases.people

import dev.joppien.swapishowcase.data.repository.PersonRepository
import dev.joppien.swapishowcase.domain.model.Person
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(
    private val personRepository: PersonRepository,
) {
    operator fun invoke(): Flow<List<Person>> {
        return personRepository.getAllPeople()
    }
}