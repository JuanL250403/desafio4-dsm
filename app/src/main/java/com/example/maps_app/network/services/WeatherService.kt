package com.example.maps_app.network.services

import com.example.maps_app.model.network.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/v1/current.json?lang=es")
    suspend fun obtenerDatosClima(@Query("key") key: String, @Query("q") coordenadas: String): WeatherResponse
}