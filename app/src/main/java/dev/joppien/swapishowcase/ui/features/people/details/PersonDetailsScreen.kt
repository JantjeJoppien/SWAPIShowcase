package dev.joppien.swapishowcase.ui.features.people.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.joppien.swapishowcase.R
import dev.joppien.swapishowcase.ui.components.EntryRow
import dev.joppien.swapishowcase.ui.theme.SWAPIAppTheme
import dev.joppien.swapishowcase.ui.theme.spacing

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
        topBar = {
            LargeTopAppBar(title = {
                Text(
                    text = if (uiState is PersonDetailsState)
                        uiState.name
                    else
                        stringResource(R.string.feature_people_tile),
                    style = MaterialTheme.typography.headlineLarge,
                )
            })
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(
                    start = MaterialTheme.spacing.paddingScreenSides,
                    end = MaterialTheme.spacing.paddingScreenSides,
                    top = MaterialTheme.spacing.paddingScreenTopBottom,
                    bottom = MaterialTheme.spacing.paddingScreenTopBottom,
                )
                .fillMaxSize()
                .verticalScroll(scrollState),
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

                    Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacingEntryColumn)) {
                        EntryRow(labelString = "Gender", entryString = person.gender)
                        EntryRow(labelString = "Birth Year", entryString = person.birthYear)
                        EntryRow(labelString = "Height", entryString = person.height)
                        EntryRow(labelString = "Mass", entryString = person.mass)
                        EntryRow(labelString = "Skin Color", entryString = person.skinColor)
                        EntryRow(labelString = "Hair Color", entryString = person.hairColor)
                        EntryRow(labelString = "Eye Color", entryString = person.eyeColor)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonDetailsScreenPreview() {
    SWAPIAppTheme {
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
    SWAPIAppTheme {
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