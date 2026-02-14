package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.ApiClient
import com.example.weatherapp.data.api.Weatherapi
import com.example.weatherapp.data.model.WeatherResponse

class WeatherRepository {

    private val weatherApi: Weatherapi =
        ApiClient.retrofit.create(Weatherapi::class.java)

    suspend fun getWeather(city: String, apiKey: String): WeatherResponse {
        return weatherApi.getWhetherByCity(city, apiKey)
    }
}
