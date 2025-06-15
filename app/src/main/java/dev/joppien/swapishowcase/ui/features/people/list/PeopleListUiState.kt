package dev.joppien.swapishowcase.ui.features.people.list

abstract class PeopleListUiState

class PeopleListLoadingState : PeopleListUiState()

class PeopleListErrorState : PeopleListUiState()

class PeopleListState(val people: List<SimplePersonUi>) : PeopleListUiState()

data class SimplePersonUi(
    val id: Int,
    val name: String,
)