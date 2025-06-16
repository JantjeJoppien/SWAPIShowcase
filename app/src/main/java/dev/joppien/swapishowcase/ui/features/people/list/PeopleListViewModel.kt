package dev.joppien.swapishowcase.ui.features.people.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.joppien.swapishowcase.domain.usecases.people.GetPeopleUseCase
import dev.joppien.swapishowcase.domain.usecases.people.RefreshPeopleUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleListViewModel @Inject constructor(
    getPeople: GetPeopleUseCase,
    private val refreshPeople: RefreshPeopleUseCase,
) : ViewModel() {

    val uiState: StateFlow<PeopleListUiState> =
        getPeople()
            .map { personItems ->
                if (personItems.isNotEmpty()) {
                    PeopleListState(personItems.map {
                        SimplePersonUi(
                            id = it.id,
                            name = it.name,
                        )
                    })
                } else {
                    PeopleListEmptyState()
                }
            }
            .onStart<PeopleListUiState> {
                emit(PeopleListLoadingState())
            }
            .catch<PeopleListUiState> { exception ->
                emit(PeopleListErrorState(exception))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = PeopleListLoadingState()
            )

    fun refreshData() {
        viewModelScope.launch {
            refreshPeople()
        }
    }

}