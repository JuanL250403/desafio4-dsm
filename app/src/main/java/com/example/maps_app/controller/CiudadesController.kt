package com.example.maps_app.controller

import com.example.maps_app.BuildConfig
import com.example.maps_app.model.mapper.toUiModel
import com.example.maps_app.model.network.GeoNamesSearchResponse
import com.example.maps_app.model.ui.CiudadUiModel
import com.example.maps_app.model.ui.ClimaUiModel
import com.example.maps_app.network.services.GeoService
import com.example.maps_app.network.services.WeatherService
import javax.inject.Inject

class CiudadesController @Inject constructor(
    private val geoService: GeoService,
    private val weatherService: WeatherService
) {
    private val geoUsername = BuildConfig.USER_GEO
    private val weatherKey = BuildConfig.KEY_WEATHER

    suspend fun obtenerCiudades(pais: String): List<CiudadUiModel> {
        val ciudades = geoService.obtenerCiudades(username = geoUsername, country = pais)

        if (!ciudades.geonames.isEmpty()) {
            val ciudadesUiModel = ciudades.geonames.map { c ->
                val clima = weatherService.obtenerDatosClima(weatherKey, "${c.lat}, ${c.lng}")

                if (clima.current != null) {

                    val climaUiModel = clima.current.toUiModel()
                    c.toUiModel(climaUiModel)

                } else {
                    c.toUiModel()
                }


            }

            return ciudadesUiModel
        }

        return listOf()
    }
}