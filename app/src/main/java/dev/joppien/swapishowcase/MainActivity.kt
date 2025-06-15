package dev.joppien.swapishowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.joppien.swapishowcase.ui.features.home.HomeScreen
import dev.joppien.swapishowcase.ui.features.movies.details.MovieDetailsScreen
import dev.joppien.swapishowcase.ui.features.movies.list.MovieListScreen
import dev.joppien.swapishowcase.ui.features.people.details.PersonDetailsScreen
import dev.joppien.swapishowcase.ui.features.people.list.PeopleListScreen
import dev.joppien.swapishowcase.ui.features.transports.list.TransportListScreen
import dev.joppien.swapishowcase.ui.features.transports.starship.StarshipDetailsScreen
import dev.joppien.swapishowcase.ui.features.transports.vehicle.VehicleDetailsScreen
import dev.joppien.swapishowcase.ui.navigation.AppArgs
import dev.joppien.swapishowcase.ui.navigation.AppDestinations
import dev.joppien.swapishowcase.ui.theme.MainTheme

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
            HomeScreen(navController = navController)
        }
        composable(route = AppDestinations.MOVIE_LIST_ROUTE) {
            MovieListScreen(navController = navController)
        }
        composable(
            route = AppDestinations.MOVIE_DETAILS_ROUTE,
            arguments = listOf(
                navArgument(AppArgs.MOVIE_ID_ARG) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt(AppArgs.MOVIE_ID_ARG)
            if (movieId != null) {
                MovieDetailsScreen(
                    navController = navController,
                    movieId = movieId,
                )
            }
        }
        composable(route = AppDestinations.PEOPLE_LIST_ROUTE) {
            PeopleListScreen(navController = navController)
        }
        composable(
            route = AppDestinations.PERSON_DETAILS_ROUTE,
            arguments = listOf(
                navArgument(AppArgs.PERSON_ID_ARG) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val personId = backStackEntry.arguments?.getInt(AppArgs.PERSON_ID_ARG)
            if (personId != null) {
                PersonDetailsScreen(
                    navController = navController,
                    personId = personId,
                )
            }
        }
        composable(route = AppDestinations.TRANSPORT_LIST_ROUTE) {
            TransportListScreen(navController = navController)
        }
        composable(
            route = AppDestinations.VEHICLE_DETAILS_ROUTE,
            arguments = listOf(
                navArgument(AppArgs.TRANSPORT_ID_ARG) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getInt(AppArgs.TRANSPORT_ID_ARG)
            if (vehicleId != null) {
                VehicleDetailsScreen(
                    navController = navController,
                    vehicleId = vehicleId,
                )
            }
        }
        composable(
            route = AppDestinations.STARSHIP_DETAILS_ROUTE,
            arguments = listOf(
                navArgument(AppArgs.TRANSPORT_ID_ARG) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val starshipId = backStackEntry.arguments?.getInt(AppArgs.TRANSPORT_ID_ARG)
            if (starshipId != null) {
                StarshipDetailsScreen(
                    navController = navController,
                    starshipId = starshipId,
                )
            }
        }

    }
}