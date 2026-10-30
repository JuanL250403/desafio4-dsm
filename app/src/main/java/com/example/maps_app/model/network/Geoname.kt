package com.example.maps_app.model.network

import com.google.gson.annotations.SerializedName

data class GeoNamesSearchResponse(
    val totalResultsCount: Int? = null,
    val geonames: List<GeonameCity> = emptyList()
)

data class GeonameCity(
    val adminCode1: String? = null,
    val lng: String? = null,
    val geonameId: Long? = null,
    val toponymName: String? = null,
    val countryId: String? = null,
    val fcl: String? = null,
    val population: Long? = null,
    val countryCode: String? = null,
    val name: String? = null,
    val fclName: String? = null,
    val adminCodes1: AdminCodes1? = null,
    val countryName: String? = null,
    val fcodeName: String? = null,
    val adminName1: String? = null,
    val lat: String? = null,
    val fcode: String? = null
)

data class AdminCodes1(
    val ISO3166_2: String? = null
)