package dev.joppien.swapishowcase.ui.features.people.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.joppien.swapishowcase.domain.usecases.people.GetPersonByIdUseCase
import dev.joppien.swapishowcase.ui.navigation.AppArgs
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getPersonById: GetPersonByIdUseCase,
) : ViewModel() {

    private val personId: Int = savedStateHandle.get<Int>(AppArgs.PERSON_ID_ARG)
        ?: throw IllegalArgumentException("Person ID not found in navigation arguments")


    val uiState: StateFlow<PersonDetailsUiState> =
        getPersonById(personId)
            .map { person ->
                if (person != null) {
                    PersonDetailsState(
                        name = person.name,
                        gender = person.gender,
                        birthYear = person.birthYear,
                        eyeColor = person.eyeColor,
                        hairColor = person.hairColor,
                        skinColor = person.skinColor,
                        height = person.height?.toString() ?: "Unknown",
                        mass = person.mass?.toString() ?: "Unknown",
                    )
                } else {
                    PersonDetailsEmptyState()
                }
            }
            .onStart<PersonDetailsUiState> {
                emit(PersonDetailsLoadingState())
            }
            .catch<PersonDetailsUiState> { exception ->
                emit(PersonDetailsErrorState(exception))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = PersonDetailsLoadingState()
            )

}