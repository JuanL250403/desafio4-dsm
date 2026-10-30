package com.example.maps_app.model.ui

data class ClimaUiModel(
    val temperatura: Double? = 0.0,
    val condicionImg: String? = "",
    val humedad: Int? = 0,
    val ultimaLectura: String? = ""
)