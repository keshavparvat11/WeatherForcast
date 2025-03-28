package com.example.wetherforecast.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wetherforecast.data.DataOrException
import com.example.wetherforecast.model.City
import com.example.wetherforecast.model.Weather
import com.example.wetherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel(){


    suspend fun getWeatherDAta(city: String): DataOrException<Weather, Boolean, Exception> {
        return repository.getweather(city)
    }
}