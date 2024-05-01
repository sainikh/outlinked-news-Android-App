# outlinked-news-Android-App


## Overview
This document serves as documentation for a pseudo-mobile application developed for Android devices. The application's primary function is to list and open outlinked news articles fetched from a provided API. It adheres to best coding practices and utilizes clean architecture principles.

## Project Structure
The project is structured following the principles of clean architecture, separating concerns into layers:

1. **Presentation Layer**: Contains Activities, Fragments, Adapters, and ViewModels responsible for handling UI logic.
2. **Domain Layer**: Consists of business logic and use cases.
3. **Data Layer**: Manages data sources and repository implementations.

## Minimum and Maximum SDK Version
- **Target SDK Version**: 33
- **Minimum SDK Version**: 21

## Programming Language
The application is developed exclusively in Kotlin, adhering to best coding practices and Kotlin idiomatic style.

## API Integration
- The application fetches news article details from the provided API: [News API](https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json).

## Features
1. **List News Articles**: The home screen lists news articles fetched from the API, displaying headlines and URLs.
2. **Open Articles**: Clicking on a listed headline opens the respective news article in a browser window.

## Clean Architecture
- **Separation of Concerns**: The project is divided into distinct layers, promoting modularity and testability.

## Best Coding Practices
- **SOLID Principles**: The codebase adheres to SOLID principles, promoting maintainability and extensibility.

## Development Environment
- **IDE**: Android Studio
- **Version Control**: Git (GitHub repository)
- **Build Tool**: Gradle

## Installation and Setup
1. Clone the repository from GitHub.
2. Open the project in Android Studio.
3. Build and run the application on an Android device or emulator.

## Usage
- Upon launching the application, users are presented with a list of news articles.
- Clicking on a headline opens the respective news article in a browser window.

## Future Improvements
- Implement caching mechanism for offline access to news articles.
- Enhance error handling and network connectivity checks for robustness.
- Explore incorporating dependency injection for better component decoupling.

## Conclusion
This pseudo-mobile news application follows clean architecture principles and best coding practices, providing a solid foundation for maintainability and extensibility. By utilizing Kotlin and adhering to architectural guidelines, the application offers a scalable solution for listing and accessing news articles on Android devices.
