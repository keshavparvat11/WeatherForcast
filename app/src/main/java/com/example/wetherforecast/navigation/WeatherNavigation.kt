package com.example.wetherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wetherforecast.scre.WeatherSplashScreen
import com.example.wetherforecast.screen.MainScreen
import com.example.wetherforecast.viewModel.MainViewModel

@Preview
@Composable
fun WeatherNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreen.SplashScreen.name
    ){
        composable(WeatherScreen.SplashScreen.name) { WeatherSplashScreen(navController) }
        composable(WeatherScreen.SearchScreen.name) {  }
        composable(WeatherScreen.MainScreen.name) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController,mainViewModel) }
        composable(WeatherScreen.FavoriteScreen.name) {  }
        composable(WeatherScreen.AboutScreen.name) {  }
        composable(WeatherScreen.SeatingScreen.name) {  }


    }
}