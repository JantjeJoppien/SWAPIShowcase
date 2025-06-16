package dev.joppien.swapishowcase.ui.features.people.list

abstract class PeopleListUiState

class PeopleListLoadingState : PeopleListUiState()

class PeopleListEmptyState : PeopleListUiState()

class PeopleListErrorState(exception: Throwable) : PeopleListUiState()

class PeopleListState(val people: List<SimplePersonUi>) : PeopleListUiState()

data class SimplePersonUi(
    val id: Int,
    val name: String,
)