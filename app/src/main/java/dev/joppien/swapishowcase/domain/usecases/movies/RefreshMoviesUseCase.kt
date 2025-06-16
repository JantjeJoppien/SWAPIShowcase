package dev.joppien.swapishowcase.domain.usecases.movies

import dev.joppien.swapishowcase.data.repository.MovieRepository
import javax.inject.Inject

class RefreshMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke() {
        movieRepository.refreshAllMoviesFromNetwork()
    }
}