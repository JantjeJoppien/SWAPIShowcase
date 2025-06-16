package dev.joppien.swapishowcase.domain.usecases.transport

import dev.joppien.swapishowcase.data.repository.StarshipRepository
import dev.joppien.swapishowcase.domain.model.Starship
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStarshipByIdUseCase @Inject constructor(
    private val starshipRepository: StarshipRepository,
) {
    operator fun invoke(id: Int): Flow<Starship?> {
        return starshipRepository.getStarshipById(id = id)
    }
}