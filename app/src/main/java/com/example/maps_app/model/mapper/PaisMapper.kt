package com.example.maps_app.model.mapper

import com.example.maps_app.model.network.CountryObject
import com.example.maps_app.model.ui.PaisDetalleUiModel
import com.example.maps_app.model.ui.PaisUiModel

fun List<CountryObject>.toUiModel(): List<PaisUiModel> = this.map { c -> c.toUiModel() }

fun CountryObject.toUiModel(): PaisUiModel =
    PaisUiModel(
        nombre = names?.official?.takeIf { it.isNotBlank() } ?: "Sin nombre",
        capital = capitals.firstOrNull()?.name?.takeIf { it.isNotBlank() } ?: "Sin capital",
        bandera = flag?.urlPng ?: "",
        codigo = codes?.alpha2.orEmpty()
    )

fun CountryObject.toUiDetalleModel(): PaisDetalleUiModel =
    PaisDetalleUiModel(
        nombre = names?.official?.takeIf { it.isNotBlank() } ?: "Sin nombre",
        capital = capitals.firstOrNull()?.name?.takeIf { it.isNotBlank() } ?: "Sin capital",
        bandera = flag?.urlPng.orEmpty(),
        region = region?.takeIf { it.isNotBlank() } ?: "Sin región",
        subRegion = subregion?.takeIf { it.isNotBlank() } ?: "Sin subregión",
        poblacion = population ?: 0,
        codigosISO = listOfNotNull(
            codes?.gec?.takeIf { it.isNotBlank() }?.let { "GEC: $it" },
            codes?.ccn3?.takeIf { it.isNotBlank() }?.let { "CCN3: $it" },
            codes?.cioc?.takeIf { it.isNotBlank() }?.let { "CIOC: $it" },
            codes?.fifa?.takeIf { it.isNotBlank() }?.let { "FIFA: $it" },
            codes?.fips?.takeIf { it.isNotBlank() }?.let { "FIPS: $it" }
        ).joinToString(", ").ifEmpty { "—" },
        monedas = currencies.mapNotNull { c ->
            val nombre = c.name?.takeIf { it.isNotBlank() } ?: return@mapNotNull null
            val detalle = listOfNotNull(
                c.code?.takeIf { it.isNotBlank() },
                c.symbol?.takeIf { it.isNotBlank() }
            ).joinToString(", ")
            if (detalle.isEmpty()) nombre else "$nombre ($detalle)"
        },
        idiomas = languages.mapNotNull { l -> l.name?.trim()?.takeIf { it.isNotEmpty() } },
        latitud = coordinates?.lat,
        longitud = coordinates?.lng
    )