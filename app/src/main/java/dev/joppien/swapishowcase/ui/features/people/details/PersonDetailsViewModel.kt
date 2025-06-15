package dev.joppien.swapishowcase.ui.features.people.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<PersonDetailsUiState>(PersonDetailsLoadingState())
    val uiState: StateFlow<PersonDetailsUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    fun refreshData() {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.emit(PersonDetailsLoadingState())
            try {
                _uiState.emit(
                    PersonDetailsState(
                        name = "Luke Skywalker",
                        gender = "Male",
                        birthYear = "19BBY",
                        eyeColor = "Blue",
                        hairColor = "Blonde",
                        skinColor = "Fair",
                        height = "172",
                        mass = "77"
                    )
                )
            } catch (e: Exception) {
                _uiState.emit(PersonDetailsErrorState())
            }
        }
    }

}