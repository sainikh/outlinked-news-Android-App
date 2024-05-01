package com.example.newsrover.feature.presentaion.utils

sealed class ScreenNavigation(val route: String) {
    object HOME : ScreenNavigation("Home")
    object WEBCLIENT : ScreenNavigation("WebClient")
}