package dev.joppien.swapishowcase.domain.usecases.movies

import dev.joppien.swapishowcase.data.repository.MovieRepository
import dev.joppien.swapishowcase.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moveRepository: MovieRepository,
) {
    operator fun invoke(): Flow<List<Movie>?> {
        return moveRepository.getAllMovies()
    }
}