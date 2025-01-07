package com.ezgieren.kotlinbasestructor.util

object Constants {
    // BaseRepository -> API Error Messages
    const val NETWORK_ERROR_MESSAGE = "Network error occurred."
    const val API_ERROR_MESSAGE = "Failed to fetch data from API."
    const val LOCAL_ERROR_MESSAGE = "Local data error occurred."
    const val NO_DATA_MESSAGE = "No data available."
    const val UNKNOWN_ERROR_MESSAGE = "An unknown error occurred."
    const val NO_DETAILS = "No additional details available."

    // Navigation Routes
    const val SPLASH_ROUTE = "splash"
    const val LOGIN_ROUTE = "login"
    const val HOME_ROUTE = "home"
    const val POST_ROUTE = "post"
    const val DETAIL_ROUTE = "detail/{userId}"

    // Query constants
    object QueryConstants {
        const val SELECT_ALL_TABLE_NAME = "SELECT * FROM tableName"
        const val GET_ALL_TABLE_NAME = "SELECT * FROM example_table"
        const val DELETE_ALL_TABLE_NAME = "DELETE FROM tableName"
        const val GET_BY_ID_TABLE_NAME = "DELETE FROM tableName"
    }

    // Database
    object Database {
        const val EXAMPLE_TABLE = "example_table"
        const val DATABASE_NAME = "example_database"
        const val COLUMN_ID = "id"
    }

    // Default UI Strings
    const val DEFAULT_USER_ID = ""
    const val LOADING_MESSAGE = "Loading..."
    const val LOGIN_SCREEN_TITLE = "Login Screen"
    const val SPLASH_SCREEN_TITLE = "Splash Screen"
    const val LOGIN_BUTTON_TEXT = "Login"
    const val BACK_BUTTON_TEXT = "Go Back"
    const val DETAILS_SCREEN_TITLE = "Details Screen"
    const val DETAILS_USER_ID_PREFIX = "User ID: "
    const val HOME_SCREEN_TITLE = "Home Screen"
    const val FETCH_DATA_BUTTON = "Fetch Posts"
    const val ERROR_MESSAGE = "Error:"
    const val DETAILS_MESSAGE = "Details:"
    const val NAME_LABEL = "Name"
    const val DESCRIPTION_LABEL = "Description"
}