package com.example.maps_app.model.ui

data class CiudadUiModel(
    val nombre: String? = null,
    val poblacion: Long? = 0,
    val latitud: String? = "",
    val longitud: String? = "",
    val esCapital: Boolean = false,
    val clima: ClimaUiModel? = null
)
