package com.example.wetherforecast.screen.mainScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wetherforecast.component.Content
import com.example.wetherforecast.component.Datadetails
import com.example.wetherforecast.component.HumidityWindPressureRow
import com.example.wetherforecast.component.SunRiseSunSetRow
import com.example.wetherforecast.data.DataOrException
import com.example.wetherforecast.model.Weather
import com.example.wetherforecast.navigation.WeatherScreen
import com.example.wetherforecast.util.formatDate
import com.example.wetherforecast.viewModel.MainViewModel
import com.example.wetherforecast.widgets.ModernTopBar

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    city: String
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
            initialValue = DataOrException(loading = true)){
            value = mainViewModel.getWeatherDAta(city)
        }.value
        if (weatherData.loading == true){
            CircularProgressIndicator()
        }
        else if (weatherData.data != null){
            Showdata(weatherData.data!!,mainViewModel,navController)
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Showdata(weather:Weather ,mainViewModel: MainViewModel,navController: NavController){

    Scaffold(topBar = {
        ModernTopBar(
            title = weather.city.name +","+ weather.city.country,
            onNavigationClick = { /* Handle menu click */ },
            navController = navController,
            isMainScreen = true,
            onActionClick = {
                navController.navigate(WeatherScreen.SearchScreen.name)
            }
        )
                   }

        ) { pa ->
        Column(modifier = Modifier.padding(top = pa.calculateTopPadding() + 4.dp)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text =formatDate( weather.list[0].dt), // Wed Nov 30
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(6.dp))

            Content(weather)
            HumidityWindPressureRow(weather)
            Divider()
            SunRiseSunSetRow(weather)
            Spacer(modifier = Modifier.height(10.dp))
            Datadetails(weather)

        }

    }

}