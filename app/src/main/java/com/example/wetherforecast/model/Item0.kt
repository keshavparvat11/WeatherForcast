package com.example.wetherforecast.model

data class Item0(
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    val feels_like: FeelsLike,
    val humidity: Int,
    val pressure: Int,
    val rain: Double,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val weather: List<WeatherX>
)