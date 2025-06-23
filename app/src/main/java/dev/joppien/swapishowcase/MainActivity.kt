package dev.joppien.swapishowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.joppien.swapishowcase.ui.features.home.HomeScreen
import dev.joppien.swapishowcase.ui.features.movies.details.MovieDetailsScreen
import dev.joppien.swapishowcase.ui.features.movies.details.MovieDetailsViewModel
import dev.joppien.swapishowcase.ui.features.movies.list.MovieListScreen
import dev.joppien.swapishowcase.ui.features.people.details.PersonDetailsScreen
import dev.joppien.swapishowcase.ui.features.people.details.PersonDetailsViewModel
import dev.joppien.swapishowcase.ui.features.people.list.PeopleListScreen
import dev.joppien.swapishowcase.ui.features.transports.list.TransportListScreen
import dev.joppien.swapishowcase.ui.features.transports.starship.StarshipDetailsScreen
import dev.joppien.swapishowcase.ui.features.transports.starship.StarshipDetailsViewModel
import dev.joppien.swapishowcase.ui.features.transports.vehicle.VehicleDetailsScreen
import dev.joppien.swapishowcase.ui.features.transports.vehicle.VehicleDetailsViewModel
import dev.joppien.swapishowcase.ui.navigation.AppAnimations.customFadeIn
import dev.joppien.swapishowcase.ui.navigation.AppAnimations.customFadeOut
import dev.joppien.swapishowcase.ui.navigation.AppAnimations.customScaleIn
import dev.joppien.swapishowcase.ui.navigation.AppAnimations.customScaleOut
import dev.joppien.swapishowcase.ui.navigation.AppAnimations.slideInFromLeft
import dev.joppien.swapishowcase.ui.navigation.AppAnimations.slideInFromRight
import dev.joppien.swapishowcase.ui.navigation.AppAnimations.slideOutToLeft
import dev.joppien.swapishowcase.ui.navigation.AppAnimations.slideOutToRight
import dev.joppien.swapishowcase.ui.navigation.AppArgs
import dev.joppien.swapishowcase.ui.navigation.AppDestinations
import dev.joppien.swapishowcase.ui.theme.SWAPIAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SWAPIAppTheme {
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
        composable(
            route = AppDestinations.HOME_ROUTE,
            enterTransition = {
                customScaleIn() + customFadeIn()
            },
            popEnterTransition = {
                customScaleIn() + customFadeIn()
            },
            exitTransition = {
                customScaleOut() + customFadeOut()
            },
            popExitTransition = {
                customScaleOut() + customFadeOut()
            },
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = AppDestinations.MOVIE_LIST_ROUTE,
            enterTransition = {
                customScaleIn() + customFadeIn()
            },
            popEnterTransition = {
                customScaleIn() + customFadeIn()
            },
            exitTransition = {
                customScaleOut() + customFadeOut()
            },
            popExitTransition = {
                customScaleOut() + customFadeOut()
            },
        ) {
            MovieListScreen(navController = navController)
        }
        composable(
            route = AppDestinations.MOVIE_DETAILS_ROUTE,
            arguments = listOf(
                navArgument(AppArgs.MOVIE_ID_ARG) {
                    type = NavType.IntType
                }
            ),
            enterTransition = { slideInFromRight() + customFadeIn() },
            exitTransition = { slideOutToLeft() + customFadeOut() },
            popEnterTransition = { slideInFromLeft() + customFadeIn() },
            popExitTransition = { slideOutToRight() + customFadeOut() },
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt(AppArgs.MOVIE_ID_ARG)
            if (movieId != null) {
                val viewModel: MovieDetailsViewModel = hiltViewModel()
                MovieDetailsScreen(
                    viewModel = viewModel,
                )
            }
        }
        composable(
            route = AppDestinations.PEOPLE_LIST_ROUTE,
            enterTransition = {
                customScaleIn() + customFadeIn()
            },
            popEnterTransition = {
                customScaleIn() + customFadeIn()
            },
            exitTransition = {
                customScaleOut() + customFadeOut()
            },
            popExitTransition = {
                customScaleOut() + customFadeOut()
            },
        ) {
            PeopleListScreen(navController = navController)
        }
        composable(
            route = AppDestinations.PERSON_DETAILS_ROUTE,
            arguments = listOf(
                navArgument(AppArgs.PERSON_ID_ARG) {
                    type = NavType.IntType
                }
            ),
            enterTransition = { slideInFromRight() + customFadeIn() },
            exitTransition = { slideOutToLeft() + customFadeOut() },
            popEnterTransition = { slideInFromLeft() + customFadeIn() },
            popExitTransition = { slideOutToRight() + customFadeOut() },
        ) { backStackEntry ->
            val personId = backStackEntry.arguments?.getInt(AppArgs.PERSON_ID_ARG)
            if (personId != null) {
                val viewModel: PersonDetailsViewModel = hiltViewModel()
                PersonDetailsScreen(
                    viewModel = viewModel,
                )
            }
        }
        composable(
            route = AppDestinations.TRANSPORT_LIST_ROUTE,
            enterTransition = {
                customScaleIn() + customFadeIn()
            },
            popEnterTransition = {
                customScaleIn() + customFadeIn()
            },
            exitTransition = {
                customScaleOut() + customFadeOut()
            },
            popExitTransition = {
                customScaleOut() + customFadeOut()
            },
        ) {
            TransportListScreen(navController = navController)
        }
        composable(
            route = AppDestinations.VEHICLE_DETAILS_ROUTE,
            arguments = listOf(
                navArgument(AppArgs.TRANSPORT_ID_ARG) {
                    type = NavType.IntType
                }
            ),
            enterTransition = { slideInFromRight() + customFadeIn() },
            exitTransition = { slideOutToLeft() + customFadeOut() },
            popEnterTransition = { slideInFromLeft() + customFadeIn() },
            popExitTransition = { slideOutToRight() + customFadeOut() },
        ) { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getInt(AppArgs.TRANSPORT_ID_ARG)
            if (vehicleId != null) {
                val viewModel: VehicleDetailsViewModel = hiltViewModel()
                VehicleDetailsScreen(viewModel = viewModel)
            }
        }
        composable(
            route = AppDestinations.STARSHIP_DETAILS_ROUTE,
            arguments = listOf(
                navArgument(AppArgs.TRANSPORT_ID_ARG) {
                    type = NavType.IntType
                }
            ),
            enterTransition = { slideInFromRight() + customFadeIn() },
            exitTransition = { slideOutToLeft() + customFadeOut() },
            popEnterTransition = { slideInFromLeft() + customFadeIn() },
            popExitTransition = { slideOutToRight() + customFadeOut() },
        ) { backStackEntry ->
            val starshipId = backStackEntry.arguments?.getInt(AppArgs.TRANSPORT_ID_ARG)
            if (starshipId != null) {
                val viewModel: StarshipDetailsViewModel = hiltViewModel()
                StarshipDetailsScreen(
                    viewModel = viewModel,
                )
            }
        }

    }
}