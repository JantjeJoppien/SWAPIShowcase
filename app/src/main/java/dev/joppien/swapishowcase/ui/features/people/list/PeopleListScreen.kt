package dev.joppien.swapishowcase.ui.features.people.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.joppien.swapishowcase.ui.navigation.AppScreens
import dev.joppien.swapishowcase.ui.theme.MainTheme
import dev.joppien.swapishowcase.ui.util.rememberPreviewNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleListScreen(
    navController: NavController,
    viewModel: PeopleListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

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
                is PeopleListLoadingState -> {
                    CircularProgressIndicator()
                }

                is PeopleListErrorState -> {
                    Text("Something went wrong!")
                    Button(onClick = { viewModel.refreshData() }) {
                        Text("Retry")
                    }
                }

                is PeopleListState -> {
                    val people = (uiState as PeopleListState).people
                    people.forEach { person ->
                        Card(
                            modifier = Modifier.clickable {
                                navController.navigate("${AppScreens.PERSON_DETAILS_SCREEN}/${person.id}")
                            }) {
                            Text(person.name)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PeopleListScreenPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        PeopleListScreen(navController = previewNavController)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PeopleListScreenDarkPreview() {
    val previewNavController = rememberPreviewNavController()
    MainTheme {
        PeopleListScreen(navController = previewNavController)
    }
}