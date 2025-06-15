package dev.joppien.swapishowcase.ui.features.people.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleListViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<PeopleListUiState>(PeopleListLoadingState())
    val uiState: StateFlow<PeopleListUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    fun refreshData() {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.emit(PeopleListLoadingState())
            try {
                _uiState.emit(
                    PeopleListState(
                        listOf(
                            SimplePersonUi(1, "Luke Skywalker"),
                            SimplePersonUi(2, "C-3PO"),
                            SimplePersonUi(3, "R2-D2"),
                        )
                    )
                )
            } catch (e: Exception) {
                _uiState.emit(PeopleListErrorState())
            }
        }
    }

}