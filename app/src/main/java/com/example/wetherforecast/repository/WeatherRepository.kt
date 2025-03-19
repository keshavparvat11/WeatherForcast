package com.example.wetherforecast.repository

import android.util.Log
import com.example.wetherforecast.data.DataOrException
import com.example.wetherforecast.model.City
import com.example.wetherforecast.model.Weather
import com.example.wetherforecast.network.WeatherApi
import javax.inject.Inject
class WeatherRepository @Inject constructor(private var api: WeatherApi) {
    suspend fun getweather(city: String) : DataOrException<Weather, Boolean, Exception>{
         val response = try {
             Log.d("REX", "getweather: 1")
             api.getWeather(q = city)
         }catch (e: Exception){
             Log.d("REX", "getweather: $e")
             return DataOrException(e = e)

         }
        Log.d("REX", "getweather: EX")
        return DataOrException(data = response)
    }
}