# LevelShoesIsmailov

LevelShoesIsmailov is an Android application that showcases products with an ability to add them to the wishlist. This project demonstrates the use of modern Android development practices, including Jetpack Compose, Hilt for dependency injection, and Retrofit for networking.

## Features

- Display a list of products fetched from an API.
- View detailed information about a product.
- Add and remove products from the wishlist.
- View all products in the wishlist.

## Tech Stack

- **Kotlin**: Programming language.
- **Jetpack Compose**: Modern UI toolkit for building native Android UI.
- **Coin**: Dependency injection library for Android.
- **Retrofit**: Type-safe HTTP client for making network requests.
- **Room**: Persistence library to manage SQLite database.
- **Navigation Component**: For managing app navigation within a single activity.
- **Coroutines**: For asynchronous programming.

## Project Structure

- **data**: Contains data-related classes such as models and network API definitions.
  - `api`: Contains API service interfaces and models for network communication.
  - `local`: Contains Room database and DAO interfaces.
- **di**: Contains Coin modules for dependency injection.
- **presentation**: Contains ViewModels and UI Composables for the presentation layer.
  - `ui`: Contains Composable functions for the app's UI screens.
  - `navigation`: Contains the navigation graph.
- **MainActivity.kt**: The entry point of the application.

## Getting Started

### Prerequisites

- Android Studio Flamingo or later.
- Kotlin 1.8.0 or later.

### Installation

1. Clone the repository:

   ```bash
   
    git clone https://github.com/BahtiIsmailov/LevelShoesIsmailov.git
