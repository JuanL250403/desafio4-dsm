package com.example.maps_app.model.mapper

import com.example.maps_app.model.network.CurrentWeather
import com.example.maps_app.model.ui.ClimaUiModel

fun CurrentWeather.toUiModel(): ClimaUiModel =
    ClimaUiModel(
        temperatura = temp_c,
        condicionImg = condition?.icon,
        humedad = humidity
    )