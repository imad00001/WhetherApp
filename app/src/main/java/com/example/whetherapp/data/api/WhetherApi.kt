package com.example.whetherapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface api {
    @GET("data/2.5/weather")
    suspend fun getWhetherByCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
        ): WeatherResponse

    // :):)
}