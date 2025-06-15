package dev.joppien.swapishowcase.ui.navigation


object AppDestinations {
    const val HOME_ROUTE = AppScreens.HOME_SCREEN
    const val MOVIE_LIST_ROUTE = AppScreens.MOVIE_LIST_SCREEN
    const val MOVIE_DETAILS_ROUTE = "${AppScreens.MOVIE_DETAILS_SCREEN}/{${AppArgs.MOVIE_ID_ARG}}"
    const val PEOPLE_LIST_ROUTE = AppScreens.PEOPLE_LIST_SCREEN
    const val PERSON_DETAILS_ROUTE =
        "${AppScreens.PERSON_DETAILS_SCREEN}/{${AppArgs.PERSON_ID_ARG}}"
    const val TRANSPORT_LIST_ROUTE = AppScreens.TRANSPORT_LIST_SCREEN
    const val VEHICLE_DETAILS_ROUTE =
        "${AppScreens.VEHICLE_DETAILS_SCREEN}/{${AppArgs.TRANSPORT_ID_ARG}}"
    const val STARSHIP_DETAILS_ROUTE =
        "${AppScreens.STARSHIP_DETAILS_SCREEN}/{${AppArgs.TRANSPORT_ID_ARG}}"
}

object AppScreens {
    const val HOME_SCREEN = "home"
    const val MOVIE_LIST_SCREEN = "movie_list"
    const val MOVIE_DETAILS_SCREEN = "movie_details"
    const val PEOPLE_LIST_SCREEN = "people_list"
    const val PERSON_DETAILS_SCREEN = "person_details"
    const val TRANSPORT_LIST_SCREEN = "transport_list"
    const val VEHICLE_DETAILS_SCREEN = "vehicle_details"
    const val STARSHIP_DETAILS_SCREEN = "starship_details"
}

object AppArgs {
    const val MOVIE_ID_ARG = "movie_id"
    const val PERSON_ID_ARG = "person_id"
    const val TRANSPORT_ID_ARG = "transport_id"
}