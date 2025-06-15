package dev.joppien.swapishowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.joppien.swapishowcase.ui.features.home.HomeScreen
import dev.joppien.swapishowcase.ui.features.movies.MovieListScreen
import dev.joppien.swapishowcase.ui.features.people.PeopleListScreen
import dev.joppien.swapishowcase.ui.features.transports.TransportListScreen
import dev.joppien.swapishowcase.ui.theme.MainTheme

object AppDestinations {
    const val HOME_ROUTE = "home"
    const val MOVIE_LIST_ROUTE = "movie_list"
    const val PEOPLE_LIST_ROUTE = "people_list"
    const val TRANSPORT_LIST_ROUTE = "transport_list"
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestinations.HOME_ROUTE
    ) {
        composable(route = AppDestinations.HOME_ROUTE) {
            HomeScreen(
                navController = navController,
            )
        }
        composable(route = AppDestinations.MOVIE_LIST_ROUTE) {
            MovieListScreen()
        }
        composable(route = AppDestinations.PEOPLE_LIST_ROUTE) {
            PeopleListScreen()
        }
        composable(route = AppDestinations.TRANSPORT_LIST_ROUTE) {
            TransportListScreen()
        }

    }
}