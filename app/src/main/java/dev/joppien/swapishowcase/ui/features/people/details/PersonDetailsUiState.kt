package dev.joppien.swapishowcase.ui.features.people.details

abstract class PersonDetailsUiState

class PersonDetailsLoadingState : PersonDetailsUiState()

class PersonDetailsEmptyState : PersonDetailsUiState()

class PersonDetailsErrorState(exception: Throwable) : PersonDetailsUiState()

class PersonDetailsState(
    val name: String,
    val gender: String,
    val birthYear: String,
    val eyeColor: String,
    val hairColor: String,
    val skinColor: String,
    val height: String,
    val mass: String,
) : PersonDetailsUiState()