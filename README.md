# Movie App

## Description

Movie App is an Android application designed to showcase a list of movies, allowing users to browse, view details, and potentially search for movies. It demonstrates modern Android development practices, including the use of Kotlin, Paging 3 for list loading, ViewModel for UI logic, and Retrofit for network calls.

## Features

*   **Browse Movies**: Displays a list of movies fetched from a remote API, loaded efficiently using the Paging 3 library.
*   **Movie Details**: (Assumed - if you have a details screen) Users can tap on a movie to view more detailed information.
*   **Loading & Error States**: Shows appropriate progress indicators during data loading and displays user-friendly error messages with a retry option if API calls fail.
*   **Search Functionality**: (If implemented) Allows users to search for specific movies.
*   **Edge-to-Edge UI**: Implements an edge-to-edge display for a more immersive user experience.

## Tech Stack & Libraries Used

*   **Kotlin**: Primary programming language.
*   **Android SDK**:
    *   XML for Layouts
    *   Activities & ViewModels
*   **Jetpack Libraries**:
    *   **ViewModel**: Manages UI-related data in a lifecycle-conscious way.
    *   **LiveData/StateFlow**: Used for observing data changes.
    *   **Paging 3**: For efficiently loading and displaying large datasets (movie lists).
    *   **Navigation Component**: (If used for navigating between screens).
    *   **Lifecycle**: For managing lifecycle events.
*   **Networking**:
    *   **Retrofit**: Type-safe HTTP client for Android and Java.
    *   **OkHttp Logging Interceptor**: For logging HTTP request and response data.
    *   **Gson**: For parsing JSON data from the API.
*   **Coroutines**: For managing background threads and asynchronous programming.
*   **Image Loading**:
    *   **Glide**: For loading and displaying movie poster images efficiently.
*   **Dependency Injection**:
    *   **Hilt**: For managing dependencies.
*   **UI**:
    *   Material Components for Android.
    *   RecyclerView for displaying lists.
    *   ConstraintLayout for flexible layouts.

## Screenshots
1. https://github.com/bharatbeladiya22/MovieAppAndroid/blob/master/screenshots/Screenshot_20251001_115437.png
2. https://github.com/bharatbeladiya22/MovieAppAndroid/blob/master/screenshots/Screenshot_20251001_115501.png
