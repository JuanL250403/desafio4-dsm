package com.example.maps_app.model.mapper

import com.example.maps_app.model.network.GeonameCity
import com.example.maps_app.model.ui.CiudadUiModel
import com.example.maps_app.model.ui.ClimaUiModel


fun GeonameCity.toUiModel(climaUiModel: ClimaUiModel? = null): CiudadUiModel =
    CiudadUiModel(
        nombre = name ?: "Sin nombre",
        poblacion = population,
        latitud = lat,
        longitud = lng,
        esCapital = fcode == "PPLC",
        clima = climaUiModel
    )