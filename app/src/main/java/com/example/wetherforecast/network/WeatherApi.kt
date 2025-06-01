package com.example.wetherforecast.network

import com.example.wetherforecast.model.Weather
import com.example.wetherforecast.util.constant
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    //https://pro.openweathermap.org/data/2.5/forecast/climate?q=London&appid=d2ef7d0e7f1810969860a767a7e0a1f5
    @GET(value = "/data/2.5/forecast/climate")
    suspend fun getWeather(
        @Query("units") units: String = "metric",
        @Query("q") q :String,
        @Query("appid") appid : String = constant.API_KEY
    ) : Weather
}