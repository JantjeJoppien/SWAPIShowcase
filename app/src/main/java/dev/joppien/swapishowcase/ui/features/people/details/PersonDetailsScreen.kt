package dev.joppien.swapishowcase.ui.features.people.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.joppien.swapishowcase.ui.features.movies.details.MovieDetailsUiState
import dev.joppien.swapishowcase.ui.theme.MainTheme
import dev.joppien.swapishowcase.ui.util.rememberPreviewNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonDetailsScreen(
    viewModel: PersonDetailsViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    PersonScreenContent(uiState = uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonScreenContent(
    uiState: PersonDetailsUiState,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("People") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (uiState) {
                is PersonDetailsLoadingState -> {
                    CircularProgressIndicator()
                }

                is PersonDetailsErrorState -> {
                    Text("Something went wrong!")
                }

                is PersonDetailsState -> {
                    val person = uiState
                    Text(person.name)
                    Text(person.gender)
                    Text(person.birthYear)
                    Text(person.eyeColor)
                    Text(person.hairColor)
                    Text(person.skinColor)
                    Text(person.height)
                    Text(person.mass)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonDetailsScreenPreview() {
    MainTheme {
        PersonScreenContent(
            PersonDetailsState(
                name = "Name",
                gender = "Gender",
                birthYear = "Birth Year",
                eyeColor = "Eye Color",
                hairColor = "Hair Color",
                skinColor = "Skin Color",
                height = "Height",
                mass = "Mass",
            )
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PersonDetailsScreenDarkPreview() {
    MainTheme {
        PersonScreenContent(
            PersonDetailsState(
                name = "Name",
                gender = "Gender",
                birthYear = "Birth Year",
                eyeColor = "Eye Color",
                hairColor = "Hair Color",
                skinColor = "Skin Color",
                height = "Height",
                mass = "Mass",
            )
        )
    }
}