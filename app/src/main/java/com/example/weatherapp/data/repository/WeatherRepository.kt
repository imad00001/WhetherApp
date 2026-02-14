package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.ApiClient
import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.data.model.WeatherResponse

class WeatherRepository {

    private val weatherApi: WeatherApi =
        ApiClient.retrofit.create(WeatherApi::class.java)

    suspend fun getWeather(city: String, apiKey: String): WeatherResponse {
        return weatherApi.getWhetherByCity(city, apiKey)
    }
}
