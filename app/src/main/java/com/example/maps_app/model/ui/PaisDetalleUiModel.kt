package com.example.maps_app.model.ui

data class PaisDetalleUiModel(
    val nombre: String? = null,
    val capital: String? = null,
    val bandera: String? = null,
    val region: String? = null,
    val subRegion: String? = null,
    val poblacion: Long? = null,
    val codigosISO: String? = null,
    val monedas: List<String>? = listOf(),
    val idiomas: List<String>? = listOf(),
    val latitud: Double? = 0.0,
    val longitud: Double? = 0.0
)
