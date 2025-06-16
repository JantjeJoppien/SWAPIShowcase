package dev.joppien.swapishowcase.domain.usecases.people

import dev.joppien.swapishowcase.data.repository.PersonRepository
import javax.inject.Inject

class RefreshPeopleUseCase @Inject constructor(
    private val personRepository: PersonRepository,
) {
    suspend operator fun invoke() {
        personRepository.refreshAllPeopleFromNetwork()
    }
}