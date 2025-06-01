package com.example.wetherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wetherforecast.scre.WeatherSplashScreen
import com.example.wetherforecast.screen.AboutScreen
import com.example.wetherforecast.screen.FavoriteScreen
import com.example.wetherforecast.screen.SettingScreen
import com.example.wetherforecast.screen.mainScreen.MainScreen
import com.example.wetherforecast.screen.searchScreen.SearchScreen
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
        composable(WeatherScreen.SearchScreen.name) {
            SearchScreen(navController)
        }
        composable(route = "${WeatherScreen.MainScreen.name}/{city}",
            arguments = listOf(
                navArgument("city") { type = NavType.StringType }
            )) { back ->
            val c = back.arguments?.getString("city") ?: "Indore"
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController,mainViewModel,city = c) }
        composable(WeatherScreen.FavoriteScreen.name) { FavoriteScreen(navController) }
        composable(WeatherScreen.AboutScreen.name) { AboutScreen(navController) }
        composable(WeatherScreen.SettingScreen.name) { SettingScreen(navController) }


    }
}