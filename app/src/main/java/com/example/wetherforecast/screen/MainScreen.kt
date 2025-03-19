package com.example.wetherforecast.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wetherforecast.data.DataOrException
import com.example.wetherforecast.model.Weather
import com.example.wetherforecast.viewModel.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun MainScreen(navController: NavController,
               mainViewModel: MainViewModel
    ){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
    Showdata(mainViewModel)
    }
}
@Composable
fun Showdata(mainViewModel: MainViewModel){
   val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
       initialValue = DataOrException(loading = true)){
       value = mainViewModel.getWeatherDAta("london")
   }.value
    if (weatherData.loading == true){
        CircularProgressIndicator()
    }
    else if (weatherData.data != null){
        Text("${weatherData.data}")
    }

}