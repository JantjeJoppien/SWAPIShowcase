package dev.joppien.swapishowcase.domain.usecases.movies

import dev.joppien.swapishowcase.data.repository.MovieRepository
import dev.joppien.swapishowcase.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(id: Int): Flow<Movie?> {
        return movieRepository.getMovieById(id = id)
    }
}