package com.example.maps_app.network.services

import com.example.maps_app.model.network.GeoNamesSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoService {

    @GET("/searchJSON?featureClass=P&cities=cities15000&maxRows=10&orderby=population")
    suspend fun obtenerCiudades(@Query("username") username: String, @Query("country") country: String): GeoNamesSearchResponse
}