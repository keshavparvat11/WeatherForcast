package com.example.wetherforecast.model

data class Weather(
    val city: City,
    val cnt: Int,
    val code: String,
    val list: List<Item0>,
    val message: Double
)