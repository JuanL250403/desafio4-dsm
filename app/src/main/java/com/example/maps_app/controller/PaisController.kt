package com.example.maps_app.controller

import android.util.Log
import com.example.maps_app.model.mapper.toUiDetalleModel
import com.example.maps_app.model.mapper.toUiModel
import com.example.maps_app.model.ui.PaisDetalleUiModel
import com.example.maps_app.model.ui.PaisUiModel
import com.example.maps_app.network.services.CountriesService
import javax.inject.Inject

class PaisController @Inject constructor(
    private val countriesService: CountriesService

) {

    suspend fun obtenerPaises(region: String): List<PaisUiModel> {
        val respuesta = countriesService.obtenerPaises(region)

        return respuesta.data?.objects?.toUiModel() ?: listOf()
    }

    suspend fun obtenerPais(codigo: String): PaisDetalleUiModel {
        val respuesta = countriesService.obtenerPais(codigo)

        return respuesta.data?.objects?.firstOrNull()?.toUiDetalleModel() ?: PaisDetalleUiModel()
    }
}